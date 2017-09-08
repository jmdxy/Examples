package com.concurrent.LatchAndBarrier.cyclicBarrier_test2;

import com.concurrent.LatchAndBarrier.Latch_test2.Runner;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//多阶段复用barrier
//会有一种情况 导致 AB 先跑完，只有cd 执行await方法，在第二阶段 或者 在统计名次阶段。 无法进行下去，线程假死 等不到“人齐”的过程
public class Runners {
    private CyclicBarrier barrier;


    public Runners(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "第一阶段跑---");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "第一阶段跑步完成！");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "休息结束！");
            System.out.println(Thread.currentThread().getName() + "第二阶段跑---");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "二段跑步完成！");
            Thread.sleep(2000);
//            System.out.println(Thread.currentThread().getName() + "休息结束！");
//            System.out.println(Thread.currentThread().getName() + "第三阶段跑---");
//            barrier.await();
//            System.out.println(Thread.currentThread().getName() + "三段跑步完成！");
//            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "统计名次！");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + "统计名次结束！");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        //会有一种情况 导致 AB 先跑完，只有cd 执行await方法，在第二阶段 或者 在统计名次阶段。 无法进行下去，线程假死 等不到“人齐”的过程
        AtomicInteger a = new AtomicInteger(0);
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println(a.getAndIncrement() + "阶段完成");
        });
        new Thread(()-> {
            Thread.currentThread().setName("A");
            Runners runner = new Runners(barrier);
            runner.run();
        }).start();
        new Thread(()-> {
            Thread.currentThread().setName("B");
            Runners runner = new Runners(barrier);
            runner.run();

        }).start();
        new Thread(()-> {
            Thread.currentThread().setName("C");
            Runners runner = new Runners(barrier);
            runner.run();
        }).start();
        new Thread(()-> {
            Thread.currentThread().setName("D");
            Runners runner = new Runners(barrier);
            runner.run();
        }).start();
    }

}
