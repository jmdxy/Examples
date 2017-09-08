package com.concurrent.semaphore;

/**
 * Created by za-wuxiaoyu on 2017/9/5.
 */
public class MyThread extends Thread{
    private MyService myService;
    public MyThread(MyService myService){
        this.myService = myService;
    }

    @Override
    public void run() {
        System.out.println("Thread :"+Thread.currentThread().getName() + "启动了！");
       myService.testMethod();
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        MyThread thread = new MyThread(service);
        thread.start();
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(service);
            threads[i].start();
        }
    }
}
