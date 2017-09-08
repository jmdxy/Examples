package com.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class RepastService {
    private final Semaphore producer = new Semaphore(10);//生产者
    private final Semaphore consumer = new Semaphore(20);//消费者
    private final int poolSize = 5;
    private ReentrantLock lock = new ReentrantLock();
    private Condition producerCondition= lock.newCondition();
    private Condition consumerCondition = lock.newCondition();
    private Object[] pool = new Object[poolSize];//生产者生产出来的物品存放 就是控制并发的数量

    private boolean isEmpty(){
        for (int i = 0; i < poolSize ; i++) {
            if(pool[i] != null ){
                return false;
            }
        }
        return true;
    }

    private boolean isFull() {
        for (int i = 0; i < poolSize ; i++) {
            if(pool[i] == null ){
                return false;
            }
        }
        return true;
    }

    private void set() {
        try{
            producer.acquire();
            lock.lock();
            while (isFull()){
                System.out.println("生产者在等待！"+Thread.currentThread().getName());
                producerCondition.await();
            }
            //模拟生产过程
            for (int i = 0; i < poolSize; i++) {
                if(pool[i] == null ){
                    pool[i] = i;
                    System.out.println("生产者生产了数据."+Thread.currentThread().getName());
                    break;
                }
            }
            consumerCondition.signalAll();
            lock.unlock();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.release();
        }
    }

    private void get() {
        try{
            consumer.acquire();
            lock.lock();
            while (isEmpty()){
                System.out.println("消费者在等待！"+Thread.currentThread().getName());
                consumerCondition.await();
            }
            //模拟消费过程
            for (int i = 0; i < poolSize; i++) {
                if(pool[i] != null ){
                    pool[i] = null;
                    System.out.println("消费者消费了数据."+Thread.currentThread().getName());
                    break;
                }
            }
            producerCondition.signalAll();
            lock.unlock();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            consumer.release();
        }
    }

    public static void main(String[] args) {
        RepastService service = new RepastService();
        Thread[] ts = new Thread[100];
        Thread[] ts2 = new Thread[100];
        for (int i = 0; i < 100; i++) {
            ts[i] = new Thread(()->{service.set();});
        }
        for (int i = 0; i < 100; i++) {
            ts2[i] =  new Thread(()->{service.get();});
        }
        for (int i = 0; i < 100; i++) {
            ts[i].start();
            ts2[i].start();
        }
    }
}
