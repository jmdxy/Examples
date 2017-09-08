package com.concurrent.semaphore.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class ListPool {
    private int poolMaxSize = 3;
    private int semaphorePermits = 5;
    private List<String> resource = new ArrayList<>();
    private Semaphore semaphore = new Semaphore(semaphorePermits);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private ListPool(){
        for (int i = 0; i < poolMaxSize; i++) {
            resource.add("ThreadName--"+i);
        }
    }

    private String get(){
        String getString = null;
        try {
            semaphore.acquire();
            lock.lock();
            while (resource.size() == 0){
                condition.await();
            }
            getString = resource.remove(0);
            lock.unlock();
        }catch (Exception e){
            e.printStackTrace();
        }
        return getString;
    }

    private void returnResource(String r) {
        lock.lock();
        resource.add(r);
        condition.signalAll();
        lock.unlock();
        semaphore.release();
    }

    public static void main(String[] args) {
        ListPool listPool = new ListPool();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String pools = listPool.get();
                System.out.println("获取到 resource :"+pools);
                listPool.returnResource(pools);
            }
        };
        for (int i = 0; i < 20; i++) {
            new Thread(r).start();
        }

    }



}
