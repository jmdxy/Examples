package com.Function;

/**
 * Created by za-wuxiaoyu on 2017/7/14.
 */
public class Function {
    public static void main(String[] args) {
        int i = 100;
        modifyTheValue(i, val-> val*val);
        System.out.println(i);
    }

    static void modifyTheValue(int valueToBeOperated, java.util.function.Function<Integer, Integer> function){
        int newValue = function.apply(valueToBeOperated);
        System.out.println(newValue);
    }


}
