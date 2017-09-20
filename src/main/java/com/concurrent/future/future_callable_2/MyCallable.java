package com.concurrent.future.future_callable_2;

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
        return call2();
    }

    private String call1() throws Exception {
        System.out.println("start!");
        int i = 1 ;
        while(i < Integer.MAX_VALUE && i!=0){
            i = i+500;
            System.out.println(i);
        }
        System.out.println("end!");
        return "年龄是："+age;
    }


    private String call2() throws Exception {
        System.out.println("start call2!");
        int i = 1 ;
        while(i < Integer.MAX_VALUE && i > 0 ){
            if(Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            i = i+500;
            System.out.println(i);
        }
        System.out.println("end!");
        return "年龄是："+age;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable m = new MyCallable(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Future<String> future =  executor.submit(m);
        System.out.println("Main A "+System.currentTimeMillis());
        System.out.println(future.isDone());//无阻塞性
        Thread.sleep(1000);
//        System.out.println( future.get());//阻塞线程  WAITING
        System.out.print("start cancle:"+future.cancel(true)+" "+future.isCancelled());
        //如果线程在running状态。是不会主动被打断的。只有 通过判断线程状态来进行跳出
    }
}
