package com.concurrent.future.future_callable_4;

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
        System.out.println("start call2!");
        Thread.sleep(3000);
        System.out.println("end!");
        return "年龄是："+age;
    }

    public static void main(String[] args) throws ExecutionException {
        MyCallable m = new MyCallable(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1));
        MyRejectException myRejectException = new MyRejectException();
        executor.setRejectedExecutionHandler(myRejectException);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.submit(r);
        executor.submit(r);
        executor.submit(r);
        executor.submit(r);
        Future<String> future =  executor.submit(m);
        System.out.println("Main A "+System.currentTimeMillis());
    }

    private static class MyRejectException implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(((FutureTask)r).toString()+"被拒绝执行！");
        }
    }

}
