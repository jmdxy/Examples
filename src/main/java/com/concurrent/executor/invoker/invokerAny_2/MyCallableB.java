package com.concurrent.executor.invoker.invokerAny_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("myCallableB start call!");
        for (int i = 0; i < 10000; i++) {
            Math.random();
            Math.random();
            Math.random();
            Math.random();
            if(i%20000 == 0) {
                System.out.println("myCallableB "+(i+1));
            }
        }
        if(1==1) {
            System.out.println("B 中断了！！！");
            throw new NullPointerException();//抛出的异常无法捕获!!!!在主线程中
            // 如果此线程提前结束并不影响其他线程运行，invokeAny仍然能获取下一个最先执行完成的线程的结果
        }
        System.out.println("myCallableB end!");
        return "returnB";
    }

    private static class MyRejectException implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(((FutureTask)r).toString()+"被拒绝执行！");
        }
    }

}
