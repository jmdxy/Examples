package com.NIO.Final;

/**
 * Created by za-wuxiaoyu on 2017/6/30.
 */
class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
public class Final {
    public static void main(String[] args){
        try {
            throw new Sneeze();
        } catch(Sneeze s) {
            System.out.println("Caught Sneeze");
        } catch(Annoyance a) {
            System.out.println("Caught Annoyance");
        }
    }


}
