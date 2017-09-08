package com.runtime;

/**
 * Created by za-wuxiaoyu on 2017/7/13.
 */
public class RunTimeDemo {
    public static void main(String[] args) throws Exception
    {
        //构造方法被私有化了。
        Runtime myRun = Runtime.getRuntime();
        System.out.println("已用内存" + myRun.totalMemory() / 1000 +"KB");
        System.out.println("最大内存" + myRun.maxMemory()/ 1000 +"KB");
        System.out.println("可用内存" + myRun.freeMemory()/ 1000 +"KB");
        String i = "";
        long start = System.currentTimeMillis();
        System.out.println("浪费内存中.....");
        for(int j = 0;j < 10000;j++)
        {
            i += j;
        }
        long end = System.currentTimeMillis();
        System.out.println("执行此程序总共花费了" + ( end - start )+ "毫秒");
        System.out.println("已用内存" + myRun.totalMemory()/1000+"KB");
        System.out.println("最大内存" + myRun.maxMemory()/1000+"KB");
        System.out.println("可用内存" + myRun.freeMemory()/1000+"KB");
        i=null;
        myRun.gc();
        System.out.println("清理垃圾后");
        System.out.println("已用内存" + myRun.totalMemory()/1000+"KB");
        System.out.println("最大内存" + myRun.maxMemory()/1000+"KB");
        System.out.println("可用内存" + myRun.freeMemory()/1000+"KB");
    }

}
