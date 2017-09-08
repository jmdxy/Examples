package com.sort;

/**
 * 分治法
 * 归并排序
 * Created by za-wuxiaoyu on 2017/8/22.
 */
public class MergeSort {
    static int time = 0;

    public static void main(String[] args) {
        int a[] = {9,7,6,5,4,3,3,2,1,11,99,88,766,45,4,5,5,33,4,5,55,56,5,7,87,34,34,56,432,53,66,44,22,11,113,44,23,24,24,265,26,27,24,367,256,365};
        int size = a.length;
        merge(a,0,size-1);
        for (int i = 0; i < size; i++) {
            System.out.println(a[i]);
        }
        System.out.println(time);
    }
    private static void merge( int a[],int begin,int end) {
        if(begin < end) {
            int mid = (begin+end) /2;
            merge(a,begin,mid);
            merge(a,mid+1,end);
            merge_together(a,begin,mid,end);
        }
    }

    /**
     * 合并两个有序的数组。
     * @param a
     * @param begin
     * @param mid
     * @param end
     */
    private static void merge_together(int a[],int begin,int mid,int end){
        int leftLen = mid - begin +1;
        int rightLen = end - mid;
        int l[] = new int[leftLen];
        int r[] = new int[rightLen];
        //赋值：
        for (int i = 0; i < leftLen; i++) {
            l[i] = a[begin+i];
        }
        for (int i = 0; i < rightLen; i++) {
            r[i] = a[mid+i+1];
        }
        for(int i=0,j=0,k=begin;k<=end;k++) {
            time++;
            if(i >= leftLen){
                a[k] = r[j];
                j++;
                continue;
            }
            if(j >= rightLen){
                a[k] = l[i];
                i++;
                continue;
            }
            if (l[i] < r[j]) {
                a[k] = l[i];
                i++;
            }else {
                a[k] = r[j];
                j++;
            }

        }
    }




//
//    private static void merge(int a[],int begin,int end){
//            if(begin  < end) {
//                int mid = (end + begin )/2;
//                merge(a,begin,mid);
//                merge(a,mid+1,end);
//                merge_sort(a,begin,mid,end);
//            }
//    }
//
//    private static void merge_sort(int a[],int begin,int mid,int end){
//        int leftLength = mid - begin +1 ;
//        int rightLength = end - mid;
//
//        int l[] = new int[leftLength];
//        int r[] = new int[rightLength];
//        System.out.println("排序start:l[]:");
//        for (int i = 0; i < leftLength; i++) {
//                l[i] = a[i+begin];
//                 System.out.println(l[i]);
//        }
//        System.out.println("排序start:r[]:");
//        for (int i = 0; i < rightLength; i++) {
//            r[i] = a[i+mid+1];
//            System.out.println(r[i]);
//        }
//
//        //将数组分成两个子数组
////        然后合并数组
//        for(int i=0,j=0,k=begin;k<=end;k++) {
//            //依次判断数组元素，按照大小放入数组a
//            if(i>=leftLength) {
//                a[k] = r[j];
//                j++;
//                continue;
//            }
//            if(j>=rightLength) {
//                a[k] = l[i];
//                i++;
//                continue;
//            }
//            if(l[i] < r[j]) {
//                a[k] = l[i];
//                i++;
//            }else {
//                a[k] = r[j];
//                j++;
//            }
//        }
//        System.out.println("排序over:");
//        for (int i = begin; i <= end; i++) {
//            System.out.println(a[i]);
//        }
//        System.out.println("#########");
//    }


}
