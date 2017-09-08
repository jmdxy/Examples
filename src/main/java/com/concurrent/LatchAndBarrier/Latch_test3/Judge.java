package com.concurrent.LatchAndBarrier.Latch_test3;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
//裁判 控制比赛开始
public class Judge {
    private CountDownLatch gun = new CountDownLatch(1);
    private CountDownLatch ready = new CountDownLatch(1);

    public void ready() {
        System.out.println("运动员准备");
        ready.countDown();
        System.out.println("运动员结束");
    }

    public void waiting() throws InterruptedException{
        ready.await();
    }
    public void go() {
        System.out.println("开始比赛！");
        gun.countDown();
    }
}
