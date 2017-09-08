package com.concurrent.LatchAndBarrier.Latch_test1;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class Runner {
    private Track track;
    public Runner(Track track){
        this.track = track;
    }
    public void ready() throws Exception{
        track.ready();
    }

    public static void main(String[] args) throws  Exception {
        int maxRunner = 10;
        CountDownLatch runners = new CountDownLatch(maxRunner);

        Track track = new Track(runners);
        for (int i = 0; i < maxRunner; i++) {
            new Thread(() -> {
                try{
                    Runner runner = new Runner(track);
                    runner.ready();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            ).start();
        }
        System.out.println("等待运动员准备！");
        runners.await();//阻塞当前线程 直到countDown 到 0 然后才继续执行
        System.out.println("都准备好了！");
    }
}
