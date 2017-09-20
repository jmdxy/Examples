package com.designMode.template;

import java.io.InputStream;

/**
 * Created by za-wuxiaoyu on 2017/9/11.
 */
public class TestTemplateOperate extends AbstractTemplateOperate<String,String>{

    public static void main(String[] args) {
        //测试 abstract方法体中参数是否是final对象。
        TestTemplateOperate t = new TestTemplateOperate();
        t.operate("success");
        System.out.println(t);
        ResultBase<String> res = new ResultBase<>();
        validation(res);
        System.out.println(res == null ? "true":"false");
    }
//
//    @Override
//    public void validation(String s, ResultBase<String> res) {
//        res = null;
//    }

        public static void validation( ResultBase<String> res) {
        res = null;
    }
}
