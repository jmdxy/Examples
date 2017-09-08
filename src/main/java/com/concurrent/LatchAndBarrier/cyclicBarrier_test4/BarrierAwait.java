package com.concurrent.LatchAndBarrier.cyclicBarrier_test4;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 *
 * 查看 await(long,TimeUnit)方法；
 * 等待时间到了会打断其他线程。
 * 其他所有在屏障点等待的线程也会抛出BrokenBarrierException 或者 InterruptedException异常 并且离开 屏障点
 *
 */
public class BarrierAwait {
    private CyclicBarrier barrier ;
    public BarrierAwait(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public void begin(int count) {
        try{
            if(getThreadName().equals("Thread-2")) {
                System.out.println("T 到了！他急着要走 只等5s！");
                System.out.println("已经到了"+barrier.getNumberWaiting()+"个选手");
                System.out.println("总共"+barrier.getParties()+"个选手");
                new Thread(()->{
                    System.out.println("15s后重置屏障");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("重置线程");
                            barrier.reset();//重置后会使所有到达屏障的线程 抛出 BrokenBarrierException
                        }
                    },15000);
                }).start();
                Thread.sleep(15000);
                barrier.await(15, TimeUnit.SECONDS);//都执行 await方法之后才会继续往下执行！ 否则所有barrier.await()都会阻塞
            }else {
                System.out.println(getThreadName()+"-到了,等待其他人");
                barrier.await();
            }

            System.out.println("everyone is coming");
            System.out.println(getThreadName()+"到达终点，开始第"+count+"阶段！");
        }catch (InterruptedException e){
            System.out.println(getThreadName()+"进入了 InterruptedException e " + barrier.isBroken());
            e.printStackTrace();
        }catch (BrokenBarrierException e){
            System.out.println(getThreadName()+"进入了 BrokenBarrierException e " + barrier.isBroken());
            e.printStackTrace();
        }catch (TimeoutException t){
            System.out.println(getThreadName()+"进入了 TimeoutException e " + barrier.isBroken());
            t.printStackTrace();
        }
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    private void test(){
        for (int i = 0; i < 1; i++) {
            begin(i+1);
        }
    }
    public static void main(String[] args) throws  Exception {
        int parties = 5;
        //都执行 await方法之后才会继续往下执行！
        //可循环使用,
        CyclicBarrier barrier = new CyclicBarrier(parties,()->{
            System.out.println("全部都到了！");
        });

        BarrierAwait m = new BarrierAwait(barrier);

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                m.test();
            }).start();
        }
    }
}
