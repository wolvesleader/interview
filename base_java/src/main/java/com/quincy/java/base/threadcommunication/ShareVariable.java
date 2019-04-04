package com.quincy.java.base.threadcommunication;

/**
 * Created by quincy on 18/3/30.
 */
public class ShareVariable {

    private volatile int a = 0;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public static void main(String[] args) {

        ShareVariable  shareVariable = new ShareVariable();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (shareVariable.getA() == 0) {
                        System.out.println("线程A");
                    } else {
                        //这就是不使用共享变量的原因，在此处不断的循环，比较耗费系统性能
                        while (true) {
                        }
                    }
                    shareVariable.setA(1);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (shareVariable.getA() == 1){
                        System.out.println("线程B");
                    }else{
                        while(true){}
                    }
                    shareVariable.setA(0);
                }
            }
        }).start();

    }

}

