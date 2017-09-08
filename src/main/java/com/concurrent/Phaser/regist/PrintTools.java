package com.concurrent.Phaser.regist;

import java.util.concurrent.Phaser;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//测试 getPhaser(), onAdvance()方法
public class PrintTools {
    public static Phaser phaser ;
//    phaser.getPhase() 表示已经到达第几个屏障，就是已经触发了几次。
    public static void method1() {
        System.out.println(name() + " A1 register Time"+System.currentTimeMillis());
        phaser.register();
        System.out.println(name() + " A1 register end Time"+ phaser.getRegisteredParties());
        System.out.println(name() + " A2 register Time"+System.currentTimeMillis());
        phaser.register();
        System.out.println(name() + " A2 register end Time"+ phaser.getRegisteredParties());
        System.out.println(name() + " A3 register Time"+System.currentTimeMillis());
        phaser.register();
        System.out.println(name() + " A3 register end Time"+ phaser.getRegisteredParties());
        System.out.println(name() + " A4 register Time"+System.currentTimeMillis());
        phaser.register();
        System.out.println(name() + " A4 register end Time"+ phaser.getRegisteredParties());
    }

    public void bulkRegister() {
        System.out.println(name() + " B1 register Time"+System.currentTimeMillis());
        phaser.bulkRegister(10);
        System.out.println(name() + " B1 register end Time"+ phaser.getRegisteredParties());
        System.out.println(name() + " B2 register Time"+System.currentTimeMillis());
        phaser.bulkRegister(10);
        System.out.println(name() + " B2 register end Time"+ phaser.getRegisteredParties());

    }

    private static String name() {
        return Thread.currentThread().getName();
    }


    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        PrintTools.phaser = phaser;
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method1();
        }).start();
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.bulkRegister();
        }).start();

        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method1();
        }).start();
    }
}
