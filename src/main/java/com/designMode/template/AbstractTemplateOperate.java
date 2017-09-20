package com.designMode.template;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public abstract class AbstractTemplateOperate<R,T> implements  Operate<R, T> {

    public ResultBase<R> operate(T o) {
        ResultBase<R> res = new ResultBase<R>();
        try {
            res.setSuccess(true);
            //验证
            this.validation(o, res);
            System.out.println(res == null ? "true":"false");
            System.out.println("validation 后 res.isSuccess:"+res.isSuccess());
            System.out.println("validation 后 res.getErrorCode:"+res.getErrorCode());
            System.out.println("validation 后 res.getErrorMsg:"+res.getErrorMsg());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void validation(T t,ResultBase<R> res) {
        res = null;
    }
}
