package com.concurrent.executor.invoker.invokeAllException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
            System.out.println("start time:"+System.currentTimeMillis());
            List<Future<String>> result = executorService.invokeAll(list);//阻塞直到 所有线程运行完
            System.out.println("end time:"+System.currentTimeMillis());
            for (int i = 0; i < 2; i++) {
                System.out.println("result="+result.get(i).get());//获取值的时候回将错误抛出
            }
        } catch (InterruptedException e) {
            System.out.println("进入了 InterruptedException 执行中捕获的异常");
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
