package com.sort;

/**
 * Created by za-wuxiaoyu on 2017/8/23.
 */
public class InsertSort {

    public static void main(String[] args) {
        int a[] = {9,7,6,5,4,3,3,2,1,11,99,88,766,45,4,5,5,33,4,5,55,56,5,7,87,34,34,56,432,53,66,44,22,11,113,44,23,24,24,265,26,27,24,367,256,365};
        insertSort(a);
        System.out.print("int a[] = {");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");
        }
        System.out.print("}");
    }
    /*
    核心原理： [9,6,45,3,132,4,5]
    从下标为1的开始比较：
     [6,9,45,3,132,4,5]
    依次：
     [6,9,45,3,132,4,5]
    依次：
     [3,6,9,45,132,4,5]
    依次：
     [3,4,6,9,45,132,5]
    依次：
     [3,4,5,6,9,45,132]
     认为之前的数据都是有序的，找到第一个比当前值小的数据，就插入到他之后。
     * 冒泡排序是比较之后的数据，插入排序是与排好的数据对比找到对应的位置插入，效率比冒泡或者选择要好。
     */
    private static void insertSort(int a[]){
        int time = 0;
        for (int i = 0; i < a.length; i++) {
            int j = i;
            while (j > 0){
                time ++;
                if(a[j] < a[j-1]) {
                    //交换
                    int b = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = b;
                }
                j--;
            }
        }
        System.out.println(time);
    }

}
