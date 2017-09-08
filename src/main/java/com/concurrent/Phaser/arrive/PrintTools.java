package com.concurrent.Phaser.arrive;

import java.util.concurrent.Phaser;

/**
 * Created by za-wuxiaoyu on 2017/9/7.
 */
//phaser.arrive(); 此方法是是phaser的parties值加1，并且不再屏障处等待，直接向下面的代码继续运行
public class PrintTools {
    public static Phaser phaser ;

    public static void method1() {
        System.out.println("X begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arriveAndAwaitAdvance();
        System.out.println("X begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        System.out.println("X2 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arriveAndAwaitAdvance();
        System.out.println("X2 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
       }

    private static String name() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception{
        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("到齐了未通过: phase="+phase + " registerPaties="+registeredParties);
                return super.onAdvance(phase,registeredParties);
            }
        };
        PrintTools.phaser = phaser;
        new Thread(()->{
            PrintTools.method1();
        }).start();

        System.out.println("A1 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("A1 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;

        System.out.println("A2 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("A2 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;

        System.out.println("B1 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("B1 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;

        System.out.println("B2 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("B2 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;

        System.out.println("C1 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("C1 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        System.out.println("旁观者 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        /**
         *
          public int awaitAdvance(int phase) {
             final Phaser root = this.root;
             long s = (root == this) ? state : reconcileState();
             int p = (int)(s >>> PHASE_SHIFT);
             if (phase < 0)
                return phase;
             if (p == phase)
                 return root.internalAwaitAdvance(phase, null);
             return p;
         }
         * 旁观者方法。
         * awaitAdvance 方法作用：如果茹灿phase 和 当前的的getPhase()值相同，则在屏障处等待，不改变parties值。
         * 否则直接越过屏障继续向下执行。 仅仅具有判断作用。
         * 不可中断线程
         */
        phaser.awaitAdvance(0);
        System.out.println("尝试中断程序！");
        Thread.currentThread().interrupt();
        System.out.println("程序并未中断！");
        System.out.println("旁观者 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        System.out.println("C2 begin\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;
        phaser.arrive();
        System.out.println("C2 end\tgetPhase="+phaser.getPhase() +"\tgetRegisterPaties="+phaser.getRegisteredParties()+"\tarrivePaties="+phaser.getArrivedParties()) ;

    }
}
