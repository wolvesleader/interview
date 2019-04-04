package com.quincy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by quincy on 2018/10/29.
 * 使用curator实现分布式锁
 */
public class DistributedLock {
    public static void main(String[] args) {
        DistributedLock distributedLock = new DistributedLock();
        distributedLock.distributedLock();
    }

    //分布式锁实现
    private void distributedLock(){
        String lock_path = "/curator_distributed_lock";


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        client.start();
        final InterProcessMutex interProcessLock = new InterProcessMutex(client,lock_path);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //主线程再次等待，其他线程执行完毕之后在执行
                    try {
                        countDownLatch.await();
                        interProcessLock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss | SSS");
                    //输出订单编号
                    String orderNumber = simpleDateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName()+" 生成的订单号:"+orderNumber);
                    try {
                        interProcessLock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        countDownLatch.countDown();


    }

    //没有使用分布式锁的实现
    private void noDistributedLock(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //主线程再次等待，其他线程执行完毕之后在执行
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss | SSS");
                    //输出订单编号
                    String orderNumber = simpleDateFormat.format(new Date());
                    System.out.println("生成的订单号:"+orderNumber);
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
