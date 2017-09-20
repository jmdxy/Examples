package com.concurrent.completionService.exception;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * Created by za-wuxiaoyu on 2017/9/14.
 */
public class RunA {

    public static void main(String[] args) {
        MyCallableA a = new MyCallableA();
        MyCallableB b = new MyCallableB();

        Executor executor = Executors.newCachedThreadPool();
        CompletionService service = new ExecutorCompletionService(executor);
        service.submit(a);
        service.submit(b);
        for (int i = 0; i < 2; i++) {
            System.out.println("");
        }
    }
}
