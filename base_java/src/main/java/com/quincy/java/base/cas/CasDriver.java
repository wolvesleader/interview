package com.quincy.java.base.cas;

/**
 * Created by quincy on 2019/1/7.
 * https://www.cnblogs.com/zyrblog/p/9864932.html
 */
public class CasDriver {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                int v = cas.get();
                boolean result = cas.compareAndSet(v, (int)(Math.random() * 100));
                System.out.println(Thread.currentThread().getName() + "="+result+":A "+v);
            }).start();
        }
    }
}

class CompareAndSwap{
    //模拟内存值
    private volatile int V;

    //获取内存值
    public synchronized int get(){
        return this.V;
    }
    //比较内存值和期望值(或者是旧值)
    private synchronized int compareAndSwap(int A,int B){
        int oldValue = this.V;
        if (oldValue == A){
            this.V = B;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int A,int B){
        return A == compareAndSwap(A,B);
    }
}
