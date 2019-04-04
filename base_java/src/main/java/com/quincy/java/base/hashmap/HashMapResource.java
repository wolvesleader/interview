package com.quincy.java.base.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by quincy on 18/3/27.
 */
public class HashMapResource implements Runnable {

    private HashMap<String ,Object> map ;

    private CountDownLatch countDownLatch;

    public Object get(String key){

            return map.get(key);


    }

    public void put(String key,Object value){

            map.put(key, value);

    }

    public HashMapResource(HashMap<String ,Object> map, CountDownLatch countDownLatch) {
        this.map = map;
        this.countDownLatch = countDownLatch;

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
