package com.quincy.java.base.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by quincy on 18/1/16.
 */
public class TestClass extends Thread {

    CountDownLatch lath;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    boolean stop = false;
    public TestClass(CountDownLatch lath){
        this.lath = lath;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop() {
        this.stop = true;
    }

    @Override
    public void run() {
        try {
            while (!this.stop){
                TimeUnit.MILLISECONDS.sleep(100L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

        byte[] buffer = new byte[0];

    }
}
