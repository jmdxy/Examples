package com.concurrent.executor.example1;

import com.concurrent.executor.threadFactory.MyThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class CreatePoolWithBound {

    private static Executor        executor;

    public CreatePoolWithBound(Executor executor) {
        this.executor = executor;
    }

    public static void test1() {
        executor = Executors.newFixedThreadPool(3);//创建最多有3个线程在运行的线程池
        /**
         return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         */
        //可以看到 只有三个线程打印了start run 同时只有3个线程运行，另外的线程要等其他线程运行完成后再运行
        //同时会复用原来的线程。控制最多线程
        for (int i = 0; i < 5; i++) {
            final int k = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "start run" + k);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " A" + k);
                System.out.println(Thread.currentThread().getName() + " run" + k + " end");
            });
        }
    }

    public static void createWithFactory() {
        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(3,factory);//创建最多有3个线程在运行的线程池
        /**
         return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         */
        //可以看到 只有三个线程打印了start run 同时只有3个线程运行，另外的线程要等其他线程运行完成后再运行
        //同时会复用原来的线程。控制最多线程
        for (int i = 0; i < 5; i++) {
            final int k = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " i'm working " + k);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " A" + k);
                System.out.println(Thread.currentThread().getName() + " i'm done " + k + " end");
            });
        }
    }
    public static void main(String[] args) {
//        CreatePoolWithBound.test1();
        CreatePoolWithBound.createWithFactory();
    }
}
