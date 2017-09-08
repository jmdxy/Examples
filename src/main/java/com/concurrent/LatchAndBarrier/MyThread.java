package com.concurrent.LatchAndBarrier;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class MyThread extends Thread {

    private Service myService;
    public MyThread(Service myService){
        this.myService = myService;
    }

    @Override
    public void run() {
        System.out.println("Thread :"+Thread.currentThread().getName() + "启动了！");
        myService.testMethod();
    }
}
