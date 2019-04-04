package com.quincy.java.base.cas;

import java.util.Random;

/**
 * Created by quincy on 2019/1/8.
 */
public class CasDriver2 {

    public static void main(String[] args) {

        CompareAndSwap2 compareAndSwap2 = new CompareAndSwap2();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int v = compareAndSwap2.get();
                    boolean b = compareAndSwap2.compareAndSet(v, (int) (Math.random() * 10));
                    System.out.println(v +"="+b);
                }
            }).start();
        }

    }

}

class CompareAndSwap2{

    private volatile int V;

    public synchronized int get(){
        return this.V;
    }

    private synchronized int compareAndSwap(int A,int B){
        int oldValue = this.V;

        if (A == oldValue){
            this.V = B;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int A,int B){
        return A == compareAndSwap(A,B);
    }

}
