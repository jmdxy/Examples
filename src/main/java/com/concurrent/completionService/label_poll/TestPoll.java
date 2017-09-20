package com.concurrent.completionService.label_poll;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class TestPoll implements Callable<String> {
    private long sleepValue;
    private String name;

    public TestPoll(String name,long sleepValue){
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
        TestPoll m = new TestPoll("call1",3000L);
        TestPoll m2 = new TestPoll("call2",1000L);
        TestPoll m3 = new TestPoll("call3",4000L);
        TestPoll m4 = new TestPoll("call4",5000L);
        TestPoll m5 = new TestPoll("call5",2000L);
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
            System.out.println(service.poll());//无阻塞性，获取不到直接返回null.
        }

    }
}
