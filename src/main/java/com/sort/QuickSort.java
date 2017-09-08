package com.sort;

/**
 * Created by za-wuxiaoyu on 2017/8/28.
 */
public class QuickSort {
    private static int time = 0;
    public static void main(String[] args) {
        int a[] = {9,7,6,5,4,3,3,2,1,11,99,88,766,45,4,5,5,7,99,23,12,15,51,50,60,44,43,42,41,40,39,33,4,5,55,56,5,7,87,34,34,56,432,53,66,44,22,11,113,44,23,24,24,265,26,27,24,367,256,365};
//        int a[] = {9,7,6,5,4,3,3,2,1,11,99,88,76};
        int size = a.length;
        quickSort(a,0,size-1);
        for (int i = 0; i < size; i++) {
            System.out.println(a[i]);
        }
        System.out.println(time);
    }

    /**
     * 分治法 快速排序
     * 找一个比较的值，比此值大就放到此值的右边，小就放到此值的左边。
     *
     * @param a
     * @param begin
     * @param end
     */
    private static void quickSort(int a[],int begin,int end){
        time++;
        System.out.println("循环前：left="+begin+",right="+(end-1));
        if(begin < end) {
            int left = begin;
            int right = end - 1;
            int midVal = a[end];
            while(left < right) {
                while(a[left] < midVal && left<right){
                    left++;
                }
                while(a[right] >= midVal && left<right){
                    right--;
                }
                System.out.println("交换-- a["+left+"]:"+a[left]+" 和 a["+right+"]:"+a[right]);
                swap(a,left,right);
            }
            if (a[left] >= a[end]){
                System.out.println("交换 a[" + left + "]："+a[left]+" 和 a[" + end + "]:"+a[end]);
                swap(a,left, end);
            }
            else{
                System.out.println("left++");
                left++;
            }
            System.out.println("循环后：left="+left+",right="+right);
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]+",");
            }
            System.out.println("");
            if(left > 0) {
                quickSort(a,begin, left - 1);
            }
            quickSort(a,left + 1, end);
        }
    }

    private static void swap(int a[],int x,int y){
        int v = a[x];
        a[x] = a[y];
        a[y]= v;
    }

}
