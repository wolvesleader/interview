package com.quincy.java.base.threadcommunication;

/**
 * Created by quincy on 18/3/30.
 */
public class WaitAndNotify {


    private volatile  int a = 0;

    public synchronized int getA() {
        return a;
    }

    public synchronized void setA(int a) {
        this.a = a;
    }


    public static void main(String[] args) {

        WaitAndNotify waitAndNotify = new WaitAndNotify();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (waitAndNotify) {
                        //条件可以根据取反条件判断，更好理解
                        while (waitAndNotify.getA() == 1) {
                            try {
                                waitAndNotify.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        waitAndNotify.setA(1);
                        System.out.println("线程A");
                        waitAndNotify.notify();
                    }
                }
            }
        },"A线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (waitAndNotify) {
                        while (waitAndNotify.getA() == 0) {
                            try {
                                waitAndNotify.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        waitAndNotify.setA(0);
                        System.out.println("线程B");
                        waitAndNotify.notify();
                    }
                }
            }
        },"B线程").start();

    }

}
