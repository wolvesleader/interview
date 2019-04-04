package com.quincy.java.base.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by quincy on 18/1/16.
 */
public class TestJVM2 {

    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(100);
        TestClass[] classes = new TestClass[500];
        for(int i = 0;i < 500; i ++){
            classes[i] = new TestClass(latch);
            classes[i].start();
        }
        TimeUnit.SECONDS.sleep(300L);

        for(int i = 0;i < 500;i ++)
            classes[i].setStop();

    }

}



