package com.concurrent.semaphore.try_acquire;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by za-wuxiaoyu on 2017/9/5.
 */
public class MyService {

    private boolean isFair = true;
    private Semaphore semaphore = new Semaphore(6,isFair);
    private ReentrantLock lock = new ReentrantLock();
    public void testMethod(){
        try{
            semaphore.acquire();
            lock.lock();
            System.out.println("Thread :"+Thread.currentThread().getName() + "进入了！");
            Thread.sleep(100);
            System.out.println("等待线程数:"+semaphore.getQueueLength());
            lock.unlock();
            System.out.println("是否有等待的线程:"+semaphore.hasQueuedThreads());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
