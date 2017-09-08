package com.designMode.Singleton;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
//默认加载实例
//加载比较慢
public class Singleton_JVM_RUN {
    private Singleton_JVM_RUN(){
        System.out.println("I'm created when jvm is run!");
    }
    private static Singleton_JVM_RUN instance = new Singleton_JVM_RUN();
    public static Singleton_JVM_RUN getInstance(){
        return instance;
    }
}
