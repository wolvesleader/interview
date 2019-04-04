package com.quincy.java.base.reentrantlock;

/**
 * Created by quincy on 18/3/21.
 */
public class MyReentrantLockDriver {


    private MyReentrantLock myReentrantLock = new MyReentrantLock();

    private int temp;

//    public int get(){
//
//        myReentrantLock.lock();
//        temp ++;
//        myReentrantLock.unlock();
//        return temp;
//
//    }

    public void foo1(){
        myReentrantLock.lock();
        System.out.println("foo1");
        foo2();
        System.out.println("sssss");
        myReentrantLock.unlock();
    }

    public void foo2(){
        myReentrantLock.lock();
        System.out.println("foo2");
        myReentrantLock.unlock();
    }

    public static void main(String[] args) {

        MyReentrantLockDriver driver = new MyReentrantLockDriver();

        new Thread(new Runnable() {
            @Override
            public void run() {
                driver.foo1();
            }
        }).start();

    }
}
