package com.quincy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by quincy on 2018/10/30.
 */
public class DistributedCounter {
    public static void main(String[] args) {
        String counter_path = "/curator_distributed_counter";

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        client.start();
        DistributedAtomicInteger distributedAtomicInteger = new DistributedAtomicInteger(client,counter_path,new RetryNTimes(3,1000));
        try {
            AtomicValue<Integer> add = distributedAtomicInteger.add(8);
            distributedAtomicInteger.increment();

            System.out.println(add.succeeded());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
