package com.concurrent.completionService.label1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class MyCallable implements Callable<String> {
    private long sleepValue;
    private String name;

    public MyCallable(String name,long sleepValue){
        this.sleepValue = sleepValue;
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        System.out.println("my name is :"+ name);
        Thread.sleep(sleepValue);//TIMED_WAITING
        return "return :" + name;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable m = new MyCallable("call1",3000L);
        MyCallable m2 = new MyCallable("call2",1000L);
        MyCallable m3 = new MyCallable("call3",4000L);
        MyCallable m4 = new MyCallable("call4",5000L);
        MyCallable m5 = new MyCallable("call5",2000L);
        List<Callable> list = new ArrayList<>();
        list.add(m);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        CompletionService<String> service = new ExecutorCompletionService<String>(executor);
        for (int i = 0; i < 5; i++) {
            service.submit(list.get(i));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("等待打印第"+(i+1)+"个返回值：");
            System.out.println(service.take().get());//按照执行速度，获取最先执行完的对象。有阻塞性。
        }

    }
}
