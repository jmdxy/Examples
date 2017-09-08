package com.concurrent.Phaser.test1;

import java.util.concurrent.Phaser;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
public class PrintTools {
    public static Phaser phaser ;
    public static void method1() {
        System.out.println(name() + "A1 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A1 end Time"+System.currentTimeMillis());
        System.out.println(name() + "A2 begin Time"+System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + "A2 end Time"+System.currentTimeMillis());
    }


    public static void method2() {
        try {
            System.out.println(name() + "A1 begin Time"+System.currentTimeMillis());
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(name() + "A1 end Time"+System.currentTimeMillis());
            Thread.sleep((int)Math.random()*1000);
            System.out.println(name() + "A2 begin Time"+System.currentTimeMillis());
            Thread.sleep(5000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(name() + "A2 end Time"+System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static String name() {
        return Thread.currentThread().getName();
    }


    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PrintTools.phaser = phaser;
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method1();
        }).start();
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method1();
        }).start();
        new Thread(()->{
            PrintTools p = new PrintTools();
            p.method2();
        }).start();
    }
}
