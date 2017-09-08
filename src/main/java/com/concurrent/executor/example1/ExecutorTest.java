package com.concurrent.executor.example1;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class ExecutorTest {

    private static Executor        executor;
    private static ExecutorService executorService;

    public ExecutorTest(Executor executor) {
        this.executor = executor;
    }

    public static void test1() {
    }

    public static void main(String[] args) {
        executor = Executors.newCachedThreadPool();//创建无界的线程池
        /**
         new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         */
        executor.execute(()->{
            System.out.println("run1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
            System.out.println("run1 end");
        });

        executor.execute(()->{
            System.out.println("run2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
            System.out.println("run2 end");
        });

        for (int i = 0; i < 5; i++) {
            final int k = i;
            executor.execute(()->{
                System.out.println("run"+k);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A"+k);
                System.out.println("run"+k+" end");
            });
        }

    }
}
