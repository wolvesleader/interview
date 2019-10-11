package com.quincy.java.base.threadcommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by quincy on 18/3/30.
 *机试题 2条线程交替，有序的打印
 */
public class LockWithCondition {


    private volatile int a = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();


    public void condition1(){

        lock.lock();
        while (a !=0 ){
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果等于0执行
        System.out.println("线程A");
        a ++;
        //唤醒线程B
        condition2.signal();
        lock.unlock();

    }

    public void condition2(){
        lock.lock();
        while (a != 1){
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程B");
        a = 0;
        condition1.signal();
        lock.unlock();

    }

    public static void main(String[] args) {
        LockWithCondition lockWithCondition = new LockWithCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lockWithCondition.condition1();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lockWithCondition.condition2();
                }
            }
        }).start();
    }

}
