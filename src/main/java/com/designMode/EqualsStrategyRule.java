package com.designMode;

import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by za-wuxiaoyu on 2017/7/14.
 */
public class EqualsStrategyRule extends Rule implements  StrategyRule {
    @Override
    public boolean checkRules() {
        if("".equals(ruleType)) {
            return true;
        }

        try( Writer w = new FileWriter("test")){

        }catch (Exception e){

        }
        return false;
    }
    public static void main(String args[]){
    }

}
