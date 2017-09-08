package com.sort;

/**
 *
 * 分治法 求大数乘积;
 *
 * Created by za-wuxiaoyu on 2017/8/25.
 *
 首先将x，y分别拆开成为两部分，可得x1，x0，y1，y0。他们的关系如下：
 x = x1 * 10m + x0；
 y = y1 * 10m + y0。其中m为正整数，m < n，且x0，y0 小于 10m。
 那么
 xy    = (x1 * 10m + x0)(y1 * 10m + y0)
 =z2 * 102m + z1 * 10m + z0，其中：
 z2 = x1 * y1；
 z1 = x1 * y0 + x0 * y1；
 z0 = x0 * y0。
 此步骤共需4次乘法，但是由Karatsuba改进以后仅需要3次乘法。因为：
 z1 = x1 * y0+ x0 * y1
 z1 = (x1 + x0) * (y1 + y0) - x1 * y1 - x0 * y0，
 故x0 * y0 便可以由加减法得到。

 所以：
 x*y = z2 *  102m +  z1 *  10m +   z0
 z2 = x1 * y1
 z1 = (x1 + x0) * (y1 + y0) - x1 * y1 - x0 * y0 =  (x1 + x0) * (y1 + y0) - x1 * y1 - z0
 z0 = x0 * y0
 Recursively computer  (x1*y1)
 Recursively computer  (x1 + x0) * (y1 + y0)
 Recursively computer  (x0 * y0)
 */
public class BigIntMultiply {
    public static void main(String[] args) {
        long a = 504;
        long b = 6068L;

        long c = a*b;
        long e = karatsuba(a,b);
        System.out.println(c);
        System.out.println(e);
    }


    public static long karatsuba(long num1, long num2){
        if ((num1 < 10) || (num2 < 10)){
            return num1*num2;
        }
        String x = String.valueOf(num1);
        String y = String.valueOf(num2);
        int n = Math.max(x.length(),y.length());
        int m = n /2;
        String x2 = x.substring(x.length() - m ,x.length());//低位
        String x1 = x.substring(0,x.length() - m);//十进制高位
        String y2 = y.substring(y.length() - m,y.length());//十进制低位
        String y1 = y.substring(0,y.length() - m);//十进制高位
        long z2 = karatsuba(Long.valueOf(x1),Long.valueOf(y1));
        long z0 = karatsuba(Long.valueOf(x2),Long.valueOf(y2));
        long z1 = karatsuba(Long.valueOf(x1)+Long.valueOf(x2),Long.valueOf(y1)+Long.valueOf(y2)) - z2 - z0;
//        (z2*10^(2*m))+((z1-z2-z0)*10^(m))+(z0);
        return z2*((long)Math.pow(10,2*m))+(z1)*((long)Math.pow(10,m)) +(z0);
    }

}
