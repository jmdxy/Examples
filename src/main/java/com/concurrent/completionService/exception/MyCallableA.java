package com.concurrent.completionService.exception;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableA begin:"+System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println("MyCallableA end:"+System.currentTimeMillis());
        return "return A";
    }
}
