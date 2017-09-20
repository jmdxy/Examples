package com.concurrent.executor.invoker.invokeAll;

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
        Thread.sleep(9000);
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
