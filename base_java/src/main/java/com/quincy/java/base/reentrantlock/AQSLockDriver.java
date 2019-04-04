package com.quincy.java.base.reentrantlock;

/**
 * Created by quincy on 18/3/26.
 */
public class AQSLockDriver {

    private int temp;

    private AQSLock aqsLock = new AQSLock();

    public int get(){
        aqsLock.lock();
        temp ++;
        aqsLock.unlock();
        return temp;
    }

    public static void main(String[] args) {

        AQSLockDriver aqsLockDriver = new AQSLockDriver();

        for (int i = 10; i > 0;i --){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + "--" + aqsLockDriver.get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
