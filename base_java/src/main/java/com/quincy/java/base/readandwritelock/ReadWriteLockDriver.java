package com.quincy.java.base.readandwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by quincy on 18/3/27.
 * 读写锁操作，线程不安全的操作演示
 */
public class ReadWriteLockDriver {

    public static void main(String[] args) {
        Map<String ,Object> map = new HashMap<>();
        int threadCount = 1000;
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // 启动threadCount个子线程
        for (int i = 1; i <= threadCount; i++) {
            System.out.println(i + "------");
            Thread thread = new Thread(new MyThread(map, countDownLatch));
            thread.start();
        }
        System.out.println(map.size());

        try {
            // 主线程等待所有子线程执行完成，再向下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.size());
    }
}
