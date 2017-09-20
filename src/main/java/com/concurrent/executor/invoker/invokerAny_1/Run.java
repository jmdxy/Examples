package com.concurrent.executor.invoker.invokerAny_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by za-wuxiaoyu on 2017/9/18.
 */
public class Run
{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Test2();
    }


    private static void Test1() throws ExecutionException, InterruptedException {
        List<Callable<String>> list = new ArrayList();
        list.add(new MyCallable());
        list.add(new MyCallableB());
        ExecutorService executorService = Executors.newCachedThreadPool();
        String getValueA = executorService.invokeAny(list);//阻塞 直到有一个运行完成
        System.out.println("hello");
        System.out.println("+++++++++++++ "+getValueA);
        System.out.println("endZZZZZZZZZZZZZZZZZZZZZZZzzz");
    }

    private static void Test2() throws ExecutionException, InterruptedException {
        List<Callable<String>> list = new ArrayList();
        list.add(new MyCallable());
        list.add(new MyCallableB2());
        ExecutorService executorService = Executors.newCachedThreadPool();
        String getValueA = executorService.invokeAny(list);//阻塞 直到有一个运行完成 运行完成后会中断其他线程.
        System.out.println("hello");
        System.out.println("+++++++++++++ "+getValueA);
        System.out.println("endZZZZZZZZZZZZZZZZZZZZZZZzzz");
    }
}
