package com.designMode.Singleton;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
// jvm初始化时 不加载 在第一次调用的时候初始化。
// 有并发问题。会重复创建实例。
public class Singleton_LazyInit {
    private Singleton_LazyInit() {
         System.out.println("LazySingleton is create");
    }
    private static Singleton_LazyInit instance = null;

    public static synchronized Singleton_LazyInit getInstance() {
         if (instance == null)  instance = new Singleton_LazyInit();
         return instance;
    }
}
