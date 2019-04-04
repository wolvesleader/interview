package com.quincy.java.base.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by quincy on 18/3/21.
 * 自己实现不可重入锁
 */
public class MyUnReentrantLock implements Lock {

    private boolean isLock = false;
    @Override
    public synchronized void lock() {

        while (isLock ){
            try {
                //没有拿到锁才会等待，第一个线程进来是能拿到锁的，所以不需要等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;

    }

    @Override
    public synchronized void unlock() {

        isLock = false;
        notify();

    }



    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
