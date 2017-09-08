package com.concurrent.LatchAndBarrier;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class Service {

    private  final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws  Exception{
        Service service = new Service();
        MyThread thread = new MyThread(service);
        thread.start();
        Thread.sleep(1000L);
        service.downLatch();
    }

    public  void testMethod(){
        try {
            System.out.println("start");
            latch.await();//阻塞
            System.out.println("end");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void downLatch() {
        System.out.println("count down!");
        latch.countDown();//countDown到0时，会将所有的await的线程唤醒.
    }
}
