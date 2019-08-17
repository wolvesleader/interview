package com.quincy.java.base.threadcommunication;

/**
 * author:quincy
 * Date:2019-08-10
 */
public class TestDriver {

    private Object obj = new Object();

    public void test(){
        synchronized (obj){
            System.out.println("ddd");
        }
    }

    public synchronized void dmeo(){
        System.out.println("demo");
    }
}
