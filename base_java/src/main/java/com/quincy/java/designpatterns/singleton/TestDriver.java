package com.quincy.java.designpatterns.singleton;

/**
 * author:quincy
 * Date:2019-06-06
 */
public class TestDriver {
    public static void main(String[] args) {

        for (int i = 0 ; i < 1000;i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 100;
                    while (count > 0){
                        Singleton3 singleton1 = Singleton3.getSingleton3();
                        System.out.println(singleton1);
                        count --;
                    }

                }
            }).start();
        }

    }
}
