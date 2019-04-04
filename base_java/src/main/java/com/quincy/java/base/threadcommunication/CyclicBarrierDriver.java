package com.quincy.java.base.threadcommunication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by quincy on 18/4/17.
 * 通过CyclicBarrier实现线程之间的通信
 */
public class CyclicBarrierDriver {


    //上课
    public void clazz(CyclicBarrier cyclicBarrier){
        System.out.println(Thread.currentThread().getName() + " 来上课啦");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("上课进行中......");
    }

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("学生全部到齐，开始上课啦");
            }
        });

        CyclicBarrierDriver cyclicBarrierDriver = new CyclicBarrierDriver();

        for(int i = 0; i < 10; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cyclicBarrierDriver.clazz(cyclicBarrier);
                }
            }).start();
        }

    }
}
