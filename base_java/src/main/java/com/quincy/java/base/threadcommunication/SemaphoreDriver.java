package com.quincy.java.base.threadcommunication;

import java.util.concurrent.Semaphore;

/**
 * Created by quincy on 18/4/17.
 */
public class SemaphoreDriver {


    //虽然有10个线程，但是同时并发的线程数量是1
    private int number = 100;
    Semaphore semaphore = new Semaphore(1);


    public void count(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " 当前线程在执行");
            System.out.println("当前输出的数字" + number --);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //通过释放和不释放来证明作用
            //semaphore.release();
        }
    }

    public static void main(String[] args) {

        SemaphoreDriver semaphoreDriver = new SemaphoreDriver();

        for (int i = 0; i < 10;i ++){
           Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    semaphoreDriver.count();
                }
            });
           thread.setName("线程 "+i);
            thread.start();
        }


    }
}
