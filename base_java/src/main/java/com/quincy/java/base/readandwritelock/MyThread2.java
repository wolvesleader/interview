package com.quincy.java.base.readandwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by quincy on 18/3/27.
 */
public class MyThread2 implements Runnable {

    private Map<String ,Object> map ;

    private CountDownLatch countDownLatch;

    private ReadWriteLock readWriteLock ;

    private Lock readLock ;
    private Lock writeLock ;

    public Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public void put(String key,Object value){
        writeLock.lock();
        try {
            map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    public MyThread2(Map<String ,Object> map, CountDownLatch countDownLatch, ReadWriteLock readWriteLock) {
        this.map = map;
        this.countDownLatch = countDownLatch;
        this.readWriteLock = readWriteLock;
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public void run() {
        // 每个线程向Map中添加100个元素
        for (int i = 1; i <= 100; i++) {
            put(Thread.currentThread().getName() + UUID.randomUUID(),"value2");
        }
        // 完成一个子线程
        countDownLatch.countDown();
    }
}
