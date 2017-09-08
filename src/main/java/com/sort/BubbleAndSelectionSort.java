package com.sort;

/**
 * Created by za-wuxiaoyu on 2017/8/30.
 */
public class BubbleAndSelectionSort {

    public static void main(String[] args) {
        int a[] = {9,7,6,5,4,3,3,2,1,11,99,88,766,45,4,5,5,33,4,5,55,56,5,7,87,34,34,56,432,53,66,44,22,11,113,44,23,24,24,265,26,27,24,367,256,365};

        selectionSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * 每次循环和初始循环的位置交换
     * 第一次循环的起始位置就是最小值。
     * @param a
     */
    public static void bubbleSort(int a[]){
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if(a[i] > a[j]){
                    swap(a,i,j);
                }
            }
        }
    }

    public static void swap(int a[],int position1,int position2){
        int temp = a[position1];
        a[position1] = a[position2];
        a[position2] = temp;
    }

    /**
     * 循环找到最小值的index 最后跟此次循环的起始位置交换。
     * 少了交换的操作。
     * @param a
     */
    public static void selectionSort(int a[]){
        int min = 0;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i; j < a.length; j++) {
                if(a[min] > a[j]){
                    min = j;
                }
            }
            swap(a,i,min);
        }
    }


}
