package com.designMode.template;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public interface Operate<R,T> {
    ResultBase<R> operate(T o);
    void validation(T t,ResultBase<R> res);
}
