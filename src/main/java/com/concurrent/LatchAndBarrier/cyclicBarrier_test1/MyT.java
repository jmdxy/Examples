package com.concurrent.LatchAndBarrier.cyclicBarrier_test1;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
//都执行 await方法之后才会继续往下执行
//可重复使用
public class MyT extends Thread {
    private CyclicBarrier barrier ;
    public MyT(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            Thread.sleep((int) (Math.random()*1000));
            System.out.println(Thread.currentThread().getName()+"-到了"+System.currentTimeMillis());
            barrier.await();//都执行 await方法之后才会继续往下执行！ 否则所有barrier.await()都会阻塞
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int num = 5;
        //都执行 await方法之后才会继续往下执行！
        //可循环使用,
        CyclicBarrier barrier = new CyclicBarrier(num,()->{
            System.out.println("全部都到了！");
        });
        MyT[] array = new MyT[num*3];
        for (int i = 0; i < num * 3; i++) {
            MyT t = new MyT(barrier);
            array[i] = t;
        }
        for (int i = 0; i < num * 3; i++) {
            array[i].start();
        }
    }
}
