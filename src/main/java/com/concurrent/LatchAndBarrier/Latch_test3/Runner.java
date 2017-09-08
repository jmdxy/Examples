package com.concurrent.LatchAndBarrier.Latch_test3;

import java.util.concurrent.CountDownLatch;

/**
 * Created by za-wuxiaoyu on 2017/9/6.
 */
public class Runner {
    private Judge judge;
    private CountDownLatch comingTag;
    private CountDownLatch waitRunningTag;
    private CountDownLatch endTag;

    public Runner(Judge judge,CountDownLatch comingTag,CountDownLatch waitRunningTag,CountDownLatch endTag) {
        this.judge = judge;
        this.comingTag = comingTag;
        this.endTag = endTag;
        this.waitRunningTag = waitRunningTag;
    }

    public void ready() {
        try{
            System.out.println("运动员看到了裁判，向这边走过来~");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() +"-到达起跑线！");
            comingTag.countDown();
            System.out.println("等待裁判说准备！");
            judge.waiting();
            System.out.println("各就各位，准备起跑！");
            Thread.sleep((int) (Math.random() * 1000));
            waitRunningTag.countDown();//运动员准备
            System.out.println(Thread.currentThread().getName() +"运动员准备完成！");
            judge.waiting();
            System.out.println(Thread.currentThread().getName() +"运动员起跑，用时不定！");
            Thread.sleep((int) (Math.random() * 1000));
            endTag.countDown();
            System.out.println(Thread.currentThread().getName() +"运动员到达终点！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int maxRunner = 10;
        Judge judge = new Judge();
        CountDownLatch comingTag = new CountDownLatch(maxRunner);
        CountDownLatch waitRunningTag = new CountDownLatch(maxRunner);
        CountDownLatch endTag = new CountDownLatch(maxRunner);
        for (int i = 0; i < maxRunner; i++) {
            new Thread(() -> {
                    Runner runner = new Runner(judge,comingTag,waitRunningTag,endTag);
                    runner.ready();
            }).start();
        }
        System.out.println("裁判等待运动员过来");
        comingTag.await();
        System.out.println("所有运动员就绪，各就各位，巡视5s.");
        Thread.sleep(5000);
        judge.ready();
        System.out.println("准备就绪等待发枪！");
        judge.go();
        System.out.println("发起枪响");
        endTag.await();
        System.out.println("所有运动员已经跑完，统计名次");

//        judge.go();//阻塞当前线程 直到countDown 到 0 然后才继续执行
//        System.out.println("比赛开始了！");
    }
}
