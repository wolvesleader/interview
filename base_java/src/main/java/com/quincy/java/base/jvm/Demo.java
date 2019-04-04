package com.quincy.java.base.jvm;

/**
 * Created by quincy on 18/1/13.
 *
 */
public class Demo {

    private Object obj = new Object();

    public Demo(){
        byte[] bytes = new byte[20*1024*1024];
    }

    public static void main(String[] args) {

        Demo d1 = new Demo();
        Demo d2 = new Demo();

        d1.obj = d2.obj;
        d2.obj = d1.obj;

        d1 = null;
        d2 = null;

        System.gc();
    }
}
