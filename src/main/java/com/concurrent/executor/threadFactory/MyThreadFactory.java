package com.concurrent.executor.threadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class MyThreadFactory implements ThreadFactory{
    private static AtomicInteger num = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("定制化线程名称或者其他属性,编号："+ num.incrementAndGet());
        return t;
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory();
        Executor executor = Executors.newCachedThreadPool(factory);
        executor.execute(()->{
            System.out.println("I'm working happy!"+System.currentTimeMillis()+"\t"+Thread.currentThread().getName());
        });
    }
}
