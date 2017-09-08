package com.concurrent.executor.example1;

import com.concurrent.executor.threadFactory.MyThreadFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class SingleThreadPool {
    private static Executor executor;

    public static void test1() {
        executor = Executors.newSingleThreadExecutor();//创建最多有3个线程在运行的线程池
        /**
         return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         */
        //可以看到 只有三个线程打印了start run 同时只有3个线程运行，另外的线程要等其他线程运行完成后再运行
        //同时会复用原来的线程。控制最多线程
        for (int i = 0; i < 5; i++) {
            final int k = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t"+ k+" start run at "+System.currentTimeMillis() );
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ "\t"+ k+ " A" );
                System.out.println(Thread.currentThread().getName()+ "\t"+ k+ " run end at:" +System.currentTimeMillis());
            });
        }
    }


    public static void createWithFactory() {
        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService executorService = Executors.newSingleThreadExecutor(factory);//创建最多有3个线程在运行的线程池
        for (int i = 0; i < 5; i++) {
            ThreadT2 t = new ThreadT2(i);
            executorService.execute(t);
        }
        executorService.shutdown();//会等所有任务执行完成后 停止线程池。
        ThreadT2 t = new ThreadT2(10);
        executorService.execute(t);//此处 已经shutdown了 后续有execute方法会抛出错误 RejectedExcutionException
    }

    public static void shutdownTest() {
        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService executorService = Executors.newSingleThreadExecutor(factory);//创建最多有3个线程在运行的线程池
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            ThreadT t = new ThreadT(i,phaser);
            executorService.execute(t);
        }
        executorService.shutdownNow();//会等所有任务执行完成后 停止线程池。
        ThreadT t = new ThreadT(10,phaser);
        executorService.execute(t);//此处 已经shutdown了 后续有execute方法会抛出错误 RejectedExcutionException

    }
    public static void shutdownTest2() {
        MyThreadFactory factory = new MyThreadFactory();
        ExecutorService executorService = new ThreadPoolExecutor(2,10,30,TimeUnit.SECONDS,new LinkedBlockingDeque<>(),factory);
        for (int i = 0; i < 4; i++) {
            executorService.execute(()->{
                for (int j = 0; j < Integer.MAX_VALUE/5000; j++) {
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                    Math.random();
                }
                System.out.println(Thread.currentThread().getName() + " 任务执行完成");
            });
        }
        List<Runnable> list = executorService.shutdownNow();
        System.out.println(list.size());

    }

    public static void main(String[] args) {
        shutdownTest2();
    }

    private static class ThreadT extends Thread{
        final int k ;
        private Phaser phaser;
        ThreadT(final int k,Phaser phaser) {
            this.k = k;
            this.phaser = phaser;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " i'm working " + k);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(k == 2){
                Integer.parseInt("a"); // 如果该线程出错了，会重新实例化一个线程继续运行
            }
            try {
                phaser.awaitAdvanceInterruptibly(0,5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " A" + k);
            System.out.println(Thread.currentThread().getName() + " i'm done " + k + " end");
        }
    }
    private static class ThreadT2 extends Thread{
        final int k ;
        ThreadT2(final int k) {
            this.k = k;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " i'm working " + k);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(k == 2){
                Integer.parseInt("a"); // 如果该线程出错了，会重新实例化一个线程继续运行
            }
            System.out.println(Thread.currentThread().getName() + " A" + k);
            System.out.println(Thread.currentThread().getName() + " i'm done " + k + " end");
        }
    }
}
