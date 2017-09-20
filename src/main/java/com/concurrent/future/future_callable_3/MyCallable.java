package com.concurrent.future.future_callable_3;

import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallable implements Callable<String> {
    private int age;
    public MyCallable(int age){
        this.age = age;
    }
    @Override
    public String call() throws Exception {
        System.out.println("start call2!");
        Thread.sleep(3000);
        if("".equals("")) throw new Exception();
        System.out.println("end!");
        return "年龄是："+age;
    }

    public static void main(String[] args) throws ExecutionException {
        MyCallable m = new MyCallable(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Future<String> future =  executor.submit(m);
        System.out.println("Main A "+System.currentTimeMillis());
        try {
            System.out.println("getValue : "+ future.get(1,TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("超时！");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("被打断");
            e.printStackTrace();
        }

        future = executor.submit(m);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
