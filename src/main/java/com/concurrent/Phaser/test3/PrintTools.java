package com.concurrent.Phaser.test3;

import java.util.concurrent.Phaser;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//测试 getPhaser(), onAdvance()方法
public class PrintTools {
    public static Phaser phaser ;
//    phaser.getPhase() 表示已经到达第几个屏障，就是已经触发了几次。
    public static void method1() {
        System.out.println(name() + "A1 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A1 end Time"+ phaser.getPhase());
        System.out.println(name() + "A2 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A2 end Time"+ phaser.getPhase());
        System.out.println(name() + "A3 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A3 end Time"+ phaser.getPhase());
        System.out.println(name() + "A4 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A4 end Time"+ phaser.getPhase());
    }

    public static void method2() {
        try {
            System.out.println(name() + "A1 begin Time"+System.currentTimeMillis());
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(name() + "A1 end Time"+phaser.getPhase());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static String name() {
        return Thread.currentThread().getName();
    }


    public static void main(String[] args) {
        Phaser phaser = new Phaser(1){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onAdvance() 被调用！");
                 super.onAdvance(phase, registeredParties);
                 return false;//返回false 说明phaser继续生效 返回true表示不等待了，phaser不生效，呈无效/销毁状态 下次将不会再进入
            }
        };
        PrintTools.phaser = phaser;
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method1();
        }).start();
    }
}
