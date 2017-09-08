package com.concurrent.Phaser.partiesGet;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.Phaser;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//测试 getPhaser(), onAdvance()方法
public class PrintTools {
    public static Phaser phaser ;
//    phaser.getPhase() 表示已经到达第几个屏障，就是已经触发了几次。
    public static void method1() {
        System.out.println(name() + " A1 begin Time"+System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println(name() + " A1 end Time"+ phaser.getRegisteredParties());
    }

    private static String name() {
        return Thread.currentThread().getName();
    }


    public static void main(String[] args) throws Exception{
        Phaser phaser = new Phaser(10);
        PrintTools.phaser = phaser;
        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                PrintTools p = new PrintTools();
                p.method1();
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("已到达："+phaser.getArrivedParties()); //到达屏障处等待的数量 已经被使用的parties数量
        System.out.println("未到达："+phaser.getUnarrivedParties());//还差多少到达屏障最大值 未被使用的parties数量
    }
}
