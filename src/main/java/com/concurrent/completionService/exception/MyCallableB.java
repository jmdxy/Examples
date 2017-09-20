package com.concurrent.completionService.exception;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB begin:"+System.currentTimeMillis());
        Thread.sleep(5000);
        Integer.parseInt("a");
        System.out.println("MyCallableB end:"+System.currentTimeMillis());
        return "return B";
    }
}
