package com.concurrent.LatchAndBarrier.Latch_test2;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class Runner {
    private Judge judge;

    public Runner(Judge judge) {
        this.judge = judge;
    }

    public void ready() throws InterruptedException {
        judge.ready();
    }

    public static void main(String[] args) throws Exception {
        int maxRunner = 100;
        Judge judge = new Judge();
        for (int i = 0; i < maxRunner; i++) {
            new Thread(() -> {
                try {
                    Runner runner = new Runner(judge);
                    runner.ready();
                    System.out.println("运动员在等待裁判");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        judge.go();//阻塞当前线程 直到countDown 到 0 然后才继续执行
        System.out.println("比赛开始了！");
    }
}
