package com.concurrent.executor.invoker.invokerAny_1;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallableB2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("myCallableB2 start call!");
        for (int i = 0; i < 400000; i++) {
            if(!Thread.currentThread().isInterrupted()){
                Math.random();
                Math.random();
                Math.random();
                Math.random();
                System.out.println("myCallableB2 "+(i+1));
            }else{
                System.out.println("异常中断！");
                throw new InterruptedException();
            }
        }
        System.out.println("myCallableB2 end!");
        return "returnB2";
    }

    public static void main(String[] args) throws ExecutionException {
        MyCallableB2 m = new MyCallableB2();
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
