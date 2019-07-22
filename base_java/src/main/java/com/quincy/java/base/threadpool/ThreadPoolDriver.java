package com.quincy.java.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:quincy
 * Date:2019-07-18
 */
public class ThreadPoolDriver {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
