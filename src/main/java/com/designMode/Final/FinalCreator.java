package com.designMode.Final;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
public final class FinalCreator {
    private final String name;
    private final String no;
    private final Double score;
    private final Map<String,String> map;

    public FinalCreator(String name, String no, Double score,Map<String,String> map) {
        this.name = name;
        this.no = no;
        this.score = score;
        this.map = map;
    }
    public String getName() {
        return name;
    }
    public String getNo() {
        return no;
    }
    public Double getScore() {
        return score;
    }
    public Map<String,String> getMap(){
        return map;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("s","dd");
        FinalCreator f = new FinalCreator("name","1",123.00d,map);
        System.out.println(map.get("s"));
        System.out.println(f.getName());
        map = new HashMap<>();
        System.out.println(map.containsKey("s"));
        System.out.println(f.getMap().containsKey("s"));
        System.out.println(map.get("s"));
    }
}
