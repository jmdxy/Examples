package com.designMode.Future;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            public void run() {// RealData的构建很慢，
            RealData<String> realData = new RealData<>(queryStr);
            future.setResult(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client = new Client();
        Data<String> data = client.request("name");
        System.out.println("请求完成");
        try{
            Thread.sleep(2000);
        }catch (Exception e){
        }
        System.out.println("结果："+data.getResult());
    }
}
