package com.concurrent.LatchAndBarrier.Latch_test1;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
//当所有运动员在跑道上都准备完成时 开始比赛
public class Track {
    private CountDownLatch maxTracks;
    public Track(CountDownLatch maxTracks) {
        this.maxTracks = maxTracks;
    }

    public void ready() throws InterruptedException{
        System.out.println("一位运动员就绪");
        maxTracks.countDown();
    }

    public boolean isAllReady() {
        return maxTracks.getCount() == 0;
    }

}
