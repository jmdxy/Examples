package com.concurrent.LatchAndBarrier.cyclicBarrier_test2;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
// barrier 屏障具有重置性 重置parties值
public class MyT extends Thread {
    private CyclicBarrier barrier ;
    public MyT(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName()+"-到了"+System.currentTimeMillis());
            barrier.await();//都执行 await方法之后才会继续往下执行！ 否则所有barrier.await()都会阻塞
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws  Exception {
        int num = 3;
        //都执行 await方法之后才会继续往下执行！
        //可循环使用,
        CyclicBarrier barrier = new CyclicBarrier(num,()->{
            System.out.println("全部都到了！");
        });
        MyT t1 = new MyT(barrier);
        t1.start();
        Thread.sleep(3000);
        System.out.println(barrier.getNumberWaiting());
        MyT t2 = new MyT(barrier);
        t2.start();
        Thread.sleep(3000);
        System.out.println(barrier.getNumberWaiting());
        MyT t3 = new MyT(barrier);
        t3.start();
        Thread.sleep(3000);
        System.out.println(barrier.getNumberWaiting());
        MyT t4 = new MyT(barrier);
        t4.start();
        Thread.sleep(3000);
        System.out.println(barrier.getNumberWaiting());

    }
}
