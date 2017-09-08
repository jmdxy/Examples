package com.designMode.Future;

/**
 * Created by za-wuxiaoyu on 2017/8/10.
 */
public class FutureData<E> implements Data<E> {
    protected RealData<E> result = null;
    protected boolean isReady = false;

    public  synchronized  void setResult(RealData<E> result) {
        if(isReady) return;
        this.result = result;
        isReady  = true;
        notifyAll();
    }

    @Override
    public synchronized E getResult() {
        while(!isReady){
            try{
//             wait();
            }finally {
            }
        }
        return result.getResult();
    }
}
