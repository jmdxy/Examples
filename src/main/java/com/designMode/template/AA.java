package com.designMode.template;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class AA {
    public String money;

    public static AA changeMoney(AA a ){
        a = new AA();
        a.money = "0";
        return a;
    }
}
