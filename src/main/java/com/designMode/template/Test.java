package com.designMode.template;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
// 检查java对象传递过程。
public class Test {
    public static void main(String[] args) {
        AA a = new AA();
        a.money = "100";
        changeMoney(a);
        System.out.println(a.money);
    }

    public static void changeMoney(AA a) {
        a = null;
    }

}
