package com.concurrent.completionService;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallable implements Callable<String> {
    private int age;
    public MyCallable(int age){
        this.age = age;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(8000);//TIMED_WAITING
        return "年龄是："+age;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable m = new MyCallable(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Future<String> future =  executor.submit(m);
        System.out.println("Main A "+System.currentTimeMillis());
        System.out.println(future.isDone());//无阻塞性
        System.out.println( future.get());//阻塞线程  WAITING
        System.out.print("Main B "+System.currentTimeMillis());
    }
}
