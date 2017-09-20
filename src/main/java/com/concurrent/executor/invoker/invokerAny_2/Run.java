package com.concurrent.executor.invoker.invokerAny_2;

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
        Test1();
    }


    private static void Test1() {
        List<Callable<String>> list = new ArrayList();
        list.add(new MyCallable());
        list.add(new MyCallableB());
        ExecutorService executorService = Executors.newCachedThreadPool();
        String getValueA = null;//阻塞 直到有一个运行完成
        try {
            getValueA = executorService.invokeAny(list);
        } catch (InterruptedException e) {
            System.out.println("进入了 InterruptedException 执行中捕获的异常");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("进入了executorService 执行中捕获的异常");//所有线程都执行出错会进入此处
            e.printStackTrace(); //如果list中的线程执行过程中都报错了。会抛出最后一个错误作为exception打印出来
        }
        System.out.println("hello");
        System.out.println("+++++++++++++ "+getValueA);
        System.out.println("endZZZZZZZZZZZZZZZZZZZZZZZzzz");
    }

}
