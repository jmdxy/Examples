package com.designMode.Singleton;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
//静态内部类初始化 实例
public class StaticSingleton {
    private StaticSingleton() {
         System.out.println("StaticSingleton is create");
    }
    private static class SingletonHolder {
        static {
            System.out.println("初始化内部类！");
        }
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance() {
         return SingletonHolder.instance;
    }

    public static void setInstance(){
        System.out.println("调用Singleton的方法，不会实例化内部类。");
    }

    public static void main(String[] args) {
        System.out.println("初始化！");
        StaticSingleton.setInstance();
       // s.getInstance();
    }
}
