package com.quincy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by quincy on 2018/10/29.
 */
public class CuratorDriver {

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        /*CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("10.211.55.17:2181",
                5000, 3000, retryPolicy);*/
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).namespace("base").build();
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/zk-book/c1","init".getBytes());
        //Thread.sleep(Integer.MAX_VALUE);


    }
}
