package com.quincy.java.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by quincy on 18/1/6.
 */
public class MyThreadPoolExecutorDriver {
    public static void main(String[] args) {
        //ThreadPoolExecutor

      // ThreadPoolExecutor  threadPoolExecutor = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Executors.newFixedThreadPool();
      //  Executors.newScheduledThreadPool(12);
        MyThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(3,5);
        for(int i =0; i < 100; i ++){
            threadPoolExecutor.executor(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " == ");
                }
            });
        }
    }
}
