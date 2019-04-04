package com.quincy.java.base.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by quincy on 18/5/27.
 * 读写锁操作，线程不安全的操作演示
 */
public class HashMapDriver {

    public static void main(String[] args) {
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        int threadCount = 1000;
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // 启动threadCount个子线程
        for (int i = 1; i <= threadCount; i++) {
            System.out.println(i + "------");
            Thread thread = new Thread(new HashMapResource(map, countDownLatch));
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
