package com.concurrent.executor.threadpool;

import com.concurrent.executor.threadFactory.MyThreadFactory;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class ThreadPoolExecutorTest_1 {


    public static void test(){
        int corePoolSize = 9;//池中线程数 包括空线程。
        int maximumPoolSize = 10;//池中线程最大限制
        long keepAliveTime = 5;//超时时间 为0 时表示 如果线程池中线程数量 > corePoolSize 并且<maxmumPoolSize 线程运行完之后会立即从池中删除。
        TimeUnit unit = TimeUnit.SECONDS;//单位
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();//用于保持任务的队列。此队列只保持 有execute提交的Runnable任务
        ThreadFactory threadFactory = new MyThreadFactory();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);

        System.out.println(executor.getPoolSize());
        System.out.println(executor.getMaximumPoolSize());
        System.out.println("");
        executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,new SynchronousQueue<>());
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getMaximumPoolSize());
    }
    //如果执行的线程数量小于 corePoolSize 马上创建线程运行这个任务 并且不放入队列中。
    public static void view() throws InterruptedException {

        int corePoolSize = 9;//池中线程数 包括空线程。
        int maximumPoolSize = 10;//池中线程最大限制
        long keepAliveTime = 5;//超时时间
        TimeUnit unit = TimeUnit.SECONDS;//单位
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();//用于保持任务的队列。此队列只保持 有execute提交的Runnable任务
        ThreadFactory threadFactory = new MyThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        Thread.sleep(400);
        executor.execute(r);
        executor.execute(r);
        System.out.println("corePoolSize:"+executor.getCorePoolSize());
        System.out.println("poolSize:"+executor.getPoolSize());
        System.out.println("queue:"+executor.getQueue().size());
        Thread.sleep(2000);
        System.out.println("corePoolSize:"+executor.getCorePoolSize());
        System.out.println("poolSize:"+executor.getPoolSize());
        System.out.println("queue:"+executor.getQueue().size());
    }

    //测试空闲线程清理的过程
    //队列使用SynchronousQueue类
    //并且线程数量<= corePoolSize
    //所以当数量keepAliveTime > 5时 也没有清除
    //如果线程数量 >corePoolSize 当数量keepAliveTime > 5时 会清除超过 corePoolSize数量的线程。
    public static void test2() throws InterruptedException {
        int corePoolSize = 7;//池中线程数 包括空线程。
        int maximumPoolSize = 8;//池中线程最大限制
        long keepAliveTime = 5;//超时时间
        TimeUnit unit = TimeUnit.SECONDS;//单位
        BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();//用于保持任务的队列。此队列只保持 有execute提交的Runnable任务
        ThreadFactory threadFactory = new MyThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        Thread.sleep(1000);
        System.out.println("corePoolSize:"+executor.getCorePoolSize());
        System.out.println("poolSize:"+executor.getPoolSize());
        System.out.println("queue:"+executor.getQueue().size());
        Thread.sleep(5000);
        System.out.println("corePoolSize:"+executor.getCorePoolSize());
        System.out.println("poolSize:"+executor.getPoolSize());
        System.out.println("queue:"+executor.getQueue().size());
    }

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    static final Runnable r =new Runnable(){
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run!"+ System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
