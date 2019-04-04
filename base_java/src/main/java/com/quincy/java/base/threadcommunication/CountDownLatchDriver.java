package com.quincy.java.base.threadcommunication;

import java.util.concurrent.CountDownLatch;

/**
 * Created by quincy on 18/4/17.
 * 计算1-100数字相加，输出结果案例来解释使用CountDownLatch的作用
 * 以及展示线程之间的通信
 */
public class CountDownLatchDriver {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int calculate(int param){

        int result = 0;

        for(int i = 1; i < param; i ++){
            result += i;
        }

        return result;
    }

    public static void main(String[] args) {

        CountDownLatchDriver countDownLatchDriver = new CountDownLatchDriver();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "计算线程开始执行");
                int calculate = countDownLatchDriver.calculate(100);
                countDownLatchDriver.setCount(calculate);
                countDownLatch.countDown();
            }
        }).start();


        System.out.println(Thread.activeCount());

//        while (Thread.activeCount() > 1){
//
//        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("计算结果为: " + countDownLatchDriver.count);

    }
}
