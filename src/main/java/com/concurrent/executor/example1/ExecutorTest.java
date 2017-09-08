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

    }
}
