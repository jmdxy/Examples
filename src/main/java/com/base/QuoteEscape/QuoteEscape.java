package com.base.QuoteEscape;

/**
 * Created by za-wuxiaoyu on 2017/9/15.
 */
public class QuoteEscape {
    public String[] state = new String[]{"a","b","c"};
    public String[] getState(){
        return state;
    }

    public static void main(String[] args) {
        QuoteEscape e = new QuoteEscape();
        String[] state2 = e.getState();
        state2 = new String[]{"a","c","b"};

        String[] state = e.state;
        System.out.println(state);
        System.out.println(state2);
    }
 }
