package com.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by za-wuxiaoyu on 2017/9/5.
 */
public class MyService {

    private boolean isFair = true;
    private Semaphore semaphore = new Semaphore(1,isFair);
    public void testMethod(){
        try{
            semaphore.acquire();
            System.out.println("Thread :"+Thread.currentThread().getName() + "进入了！");
            Thread.sleep(1000);
            System.out.println("等待线程数:"+semaphore.getQueueLength());
            System.out.println("是否有等待的线程:"+semaphore.hasQueuedThreads());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
