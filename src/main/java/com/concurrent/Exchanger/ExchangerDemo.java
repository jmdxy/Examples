package com.concurrent.Exchanger;

import java.util.concurrent.Exchanger;

/**
 * Created by za-wuxiaoyu on 2017/9/6. 功能:可以在两个线程之间传输数据。主要学习exchange()方法。
 * exchange() 方法是阻塞的。此方法调用后，其他线程如果没有来获取数据 则一直阻塞。
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        a.start();
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
                        + exchanger.exchange("hello")); //阻塞线程
                System.out.println("A Stop;" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

