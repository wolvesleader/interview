package com.quincy.java.base.threadcommunication;

/**
 * Created by quincy on 18/4/17.
 */
public class JoinDriver {

    public void thread1Run(){
        System.out.println("线程1执行");
    }

    public void thread2Run(){
        System.out.println("join线程执行");
    }

    public static void main(String[] args) throws InterruptedException {

        JoinDriver joinDriver = new JoinDriver();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                joinDriver.thread1Run();
            }
        });

        thread1.start();

        thread1.join();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                joinDriver.thread2Run();
            }
        });

        thread2.start();
    }
}
