package com.quincy.java.base.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by quincy on 18/3/21.
 * 自己实现可重入锁
 * 重入锁关键技术点:
 * 1.当前持有锁的线程是不是又一次获取到了锁
 * 2.获取锁的计数器
 */
public class MyReentrantLock implements Lock {

    private boolean isLock = false;

    private Thread currentThread = null;

    //获取锁次数计数器
    private int lockCount = 0;

    @Override
    public synchronized void lock() {

        while (isLock && currentThread != Thread.currentThread()){
            try {
                //没有拿到锁才会等待，第一个线程进来是能拿到锁的，所以不需要等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
        lockCount ++;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {

        if(currentThread == Thread.currentThread()){
            lockCount --;
            if(lockCount == 0){
                isLock = false;
                notify();
            }
        }
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
