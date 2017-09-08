package com.concurrent.executor.example1;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class ThreadReuse extends Thread{

    private String name;
    public ThreadReuse(String name ) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name() +"\t"+ name +" i'm working");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name() +"\t"+ name +" i'm done!");
    }
    private String name() {
        return Thread.currentThread().getName();
    }

    /*
    * 通过线程名称可以发现 线程被复用了。
    */
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();//创建无界的线程池
        /**
         new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         */
        for (int i = 0; i < 5; i++) {
            final int k = i;
            executor.execute(new ThreadReuse(""+(i+1)));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            final int k = i;
            executor.execute(new ThreadReuse(""+(i+1)));
        }


    }
}
