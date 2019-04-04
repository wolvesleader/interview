package com.quincy.java.base.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by quincy on 18/3/26.
 * 根据AbstractQueuedSynchronizer提供的API实现我们自己的非公平锁
 */
public class AQSLock implements Lock{


    private MySync mySync;

    public AQSLock(){
        mySync = new MySync();
    }

    private class MySync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            //开始实现我们自己的锁的加锁过程

            //获取到锁的状态
            int state = getState();
            final Thread current = Thread.currentThread();

            if (state ==0){//如果锁的状态为0，说明锁处于空闲状态可以被获取
                //设置锁的状态
                if (compareAndSetState(0,arg)){
                    //把当前线程存储起来
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true; //获取锁成功，返回值
                }
            }
            //处理锁重入的过程
            else if(current == getExclusiveOwnerThread()){//当前的线程之前并没有把锁释放，再次获取到了锁
                int currentState = state + arg;
                setState(currentState);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int state = getState();

            int currentState = state - arg;
            //如果当前的线程，不是之前获取到锁的线程，需要抛出异常
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new RuntimeException();

            boolean releaseState = false;
            if (currentState == 0){
               //释放锁成功
                releaseState = true;
                setExclusiveOwnerThread(null);
            }
            //再次跟新当前锁的状态
            setState(currentState);
            return releaseState;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }

}
