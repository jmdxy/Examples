package com.concurrent.LatchAndBarrier.cyclicBarrier_test3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 *
 * 查看 isBroken方法的使用。
 *
 * 类CyclicBarrier对于线程终端 interrupt处理会使用全有或者全无的破坏模型，如果有个线程中断，或者超时提前离开了屏障点（await()）。
 * 其他所有在屏障点等待的线程也会抛出BrokenBarrierException 或者 InterruptedException异常 并且离开 屏障点。
 *
 */
public class BarrierInterrupt {
    private CyclicBarrier barrier ;
    public BarrierInterrupt(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public void begin(int count) {
        try{
            if(getThreadName().equals("Thread-2")) {
                System.out.println("T 到了！他肚子疼！");
                Thread.sleep(5000);
//                Integer.parseInt("a");
                Thread.currentThread().interrupt();
            }else {
                Thread.sleep(7000);
            }
            System.out.println(getThreadName()+"-到了,等待其他人");
            barrier.await();//都执行 await方法之后才会继续往下执行！ 否则所有barrier.await()都会阻塞
            System.out.println("everyone is coming");
            System.out.println(getThreadName()+"到达终点，开始第"+count+"阶段！");
        }catch (InterruptedException e){
            System.out.println(getThreadName()+"进入了 InterruptedException e " + barrier.isBroken());
            e.printStackTrace();
        }catch (BrokenBarrierException e){
            System.out.println(getThreadName()+"进入了 BrokenBarrierException e " + barrier.isBroken());
            e.printStackTrace();
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
        int parties = 4;
        //都执行 await方法之后才会继续往下执行！
        //可循环使用,
        CyclicBarrier barrier = new CyclicBarrier(4,()->{
            System.out.println("全部都到了！");
        });

        BarrierInterrupt m = new BarrierInterrupt(barrier);

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                m.test();
            }).start();
        }
    }
}
