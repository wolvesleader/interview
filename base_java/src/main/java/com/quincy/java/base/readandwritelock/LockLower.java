package com.quincy.java.base.readandwritelock;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by quincy on 18/4/1.
 * 锁降级
 */
public class LockLower {
    private Map<String ,Object> map ;

    private CountDownLatch countDownLatch;

    private ReadWriteLock readWriteLock ;

    private Lock readLock ;
    private Lock writeLock ;

    private volatile boolean isUpdate = false;


    public void wirteLockToReadLock(){
        readLock.lock();
        if (isUpdate){
            readLock.unlock();
            writeLock.lock();
            map.put("","");
            //锁降级
            readLock.lock();
            writeLock.unlock();
        }

        Object obj =  map.get("");
        System.out.println(obj);

    }

    public LockLower(Map<String ,Object> map, CountDownLatch countDownLatch, ReadWriteLock readWriteLock) {
        this.map = map;
        this.countDownLatch = countDownLatch;
        this.readWriteLock = readWriteLock;
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

}
