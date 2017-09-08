package com.concurrent.executor.example1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * Created by za-wuxiaoyu on 2017/9/8.
 */
public class ExecutorTestAssert {

    private static Executor executor ;
    private static ExecutorService executorService ;
    public ExecutorTestAssert(Executor executor){
        this.executor = executor;
    }

    public static void test1() {
    }

    public static void main(String[] args) {
        assert true;
        System.out.println("true");
        assert false;
        System.out.println("false");
    }
}
