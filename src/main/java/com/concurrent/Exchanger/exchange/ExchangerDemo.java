package com.concurrent.Exchanger.exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by za-wuxiaoyu on 2017/9/6. 功能:可以在两个线程之间传输数据。主要学习exchange()方法。
 * exchange() 方法是阻塞的。此方法调用后，其他线程如果没有来获取数据 则一直阻塞。
 * exchange("Hello A",100l, TimeUnit.MINUTES);在指定时间内没有获取数据则抛出异常
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        ThreadB b = new ThreadB(exchanger);
        a.start();
        b.start();
        System.out.println("main end!");
    }


    private static class ThreadA extends Thread {
        private Exchanger<String> exchanger;

        public ThreadA(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "线程A中获取编程B的值："
                        + exchanger.exchange("hello A")); //阻塞线程
                exchanger.exchange("Hello A",100l, TimeUnit.MINUTES);
                System.out.println("A Stop;" + Thread.currentThread().getName());
            } catch (InterruptedException | TimeoutException t) {
                t.printStackTrace();
            }
        }
    }


    private static class ThreadB extends Thread {
        private Exchanger<String> exchanger;

        public ThreadB(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "线程B中获取编程B的值："
                        + exchanger.exchange("hello B")); //阻塞线程
                System.out.println("B Stop;" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



