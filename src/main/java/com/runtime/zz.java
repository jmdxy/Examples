package com.runtime;

import java.util.ArrayList;

/**
 * Created by za-wuxiaoyu on 2017/8/24.
 */
public class zz {
    public static ArrayList<String> foo(ArrayList<?> list) {
        return (ArrayList<String>)list;
    }

    public static Class<?> getElementClass(ArrayList<String> list) {
        Object elem = list.get(0);
        return elem.getClass();
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(123);
        Class<?> clz = getElementClass(foo((ArrayList) list));
        System.out.println(clz);
    }
}
