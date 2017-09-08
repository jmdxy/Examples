package com.concurrent.Phaser.awaitInterrupt;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//phaser.arrive(); 此方法是是phaser的parties值加1，并且不再屏障处等待，直接向下面的代码继续运行
public class PrintTools {
    public static Phaser phaser ;

    public static void method1() {
        try {
            System.out.println("X begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
            //读取线程当前interrupt标志 如果是打断了的 就就抛出异常
            phaser.awaitAdvanceInterruptibly(0);//符合栏位数就wait 不符合就继续往下执行。
            phaser.awaitAdvanceInterruptibly(0,30, TimeUnit.SECONDS);//；在指定时间内栏位未变，则抛出异常 否则 则继续往下执行
            System.out.println("X begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        }catch (InterruptedException e){
            System.out.println("进入中断异常！");
            e.printStackTrace();
        }catch (TimeoutException e){

            e.printStackTrace();
        }
       }

    private static String name() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception{
        Phaser phaser = new Phaser(2);
        PrintTools.phaser = phaser;
        Thread t = new Thread(()->{
            PrintTools.method1();
        });
        t.start();
        System.out.println("尝试中断程序！");
        t.interrupt();
        phaser.forceTermination();//强制使phaser对象失效！仅仅是将屏障取消，线程将继续向下执行，并不出现异常
        phaser.isTerminated();//判断屏障是否失效
    }
}
