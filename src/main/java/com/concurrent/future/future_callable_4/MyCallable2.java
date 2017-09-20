package com.concurrent.future.future_callable_4;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallable2 implements Callable<String> {
    private int age;
    public MyCallable2(int age){
        this.age = age;
    }
    @Override
    public String call() throws Exception {
        System.out.println("start call2!");
        Thread.sleep(3000);
        System.out.println("end!");
        return "年龄是："+age;
    }

    public static void main(String[] args) {
        MyCallable2 m = new MyCallable2(100);
        MyThreadFactory factory = new MyThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1),factory);
        MyRejectException myRejectException = new MyRejectException();
        executor.setRejectedExecutionHandler(myRejectException);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                Integer.parseInt("1a");
            }
        };
        executor.execute(r);//可用自定义的factory定义统一的异常捕获
        executor.execute(r);
        try {
            executor.submit(r);
            executor.submit(r);
            executor.submit(r);//报错无法捕捉异常
            Future<String> future =  executor.submit(m);
            System.out.println("Main A "+System.currentTimeMillis());
            System.out.println("Main A "+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class MyRejectException implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(((FutureTask)r).toString()+"被拒绝执行！");
        }
    }

}
