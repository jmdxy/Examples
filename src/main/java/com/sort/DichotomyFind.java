package com.sort;

import com.sun.imageio.plugins.common.InputStreamAdapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

/**
 * 分治法
 * 二分法查找
 * 复杂度 logN
 * Created by za-wuxiaoyu on 2017/8/25.
 */
public class DichotomyFind {
    static int a[] = {1,2,3,3,4,4,4,5,5,5,5,5,6,7,7,9,11,11,22,23,24,24,24,26,27,33,34,34,44,44,45,53,55,56,56,66,87,88,99,113,256,265,365,367,432,766};

    public static void main(String[] args) throws Exception{
        InputStreamReader reader = new InputStreamReader(System.in);
        System.out.println(a.length);
        CharBuffer buffer = CharBuffer.allocate(10);
        int b  = reader.read(buffer);
        char[] chars = null;
        buffer.flip();
        while (buffer.hasRemaining()) {
            chars = new char[buffer.remaining()];
            buffer.get(chars);
            System.out.print(chars);
        }
        String s = new String(chars).replace("\n","");
        int d = Integer.parseInt(s);
        int index = get(a,d,0,a.length-1);
        System.out.println(index);
    }

    public static int get(int a[],int x,int begin,int end){
        int n = (begin + end) / 2;
        if(x >= a[n]) {
            if(  x == a[n]){
                System.out.println(a[n]);
                return n;
            }
            return get(a,x,n+1,end);
        }else {
            return get(a,x,begin,n);
        }
    }

}
