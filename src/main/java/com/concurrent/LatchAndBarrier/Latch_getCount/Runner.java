package com.concurrent.LatchAndBarrier.Latch_getCount;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class Runner {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
        System.out.println("latch count"+latch.getCount());
        latch.countDown();
    }
}
