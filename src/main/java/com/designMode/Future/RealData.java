package com.designMode.Future;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
public class RealData<E> implements Data<E> {
    protected final E result;

    public RealData(E para) {
        //RealData的构造可能很慢，需要用户等待很久，这里使用sleep模拟
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
        }
        try {
            //这里使用sleep，代替一个很慢的操作过程
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
       result = para;
    }
    public E getResult() {
        return result;
    }
}
