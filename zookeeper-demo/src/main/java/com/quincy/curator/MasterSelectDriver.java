package com.quincy.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by quincy on 2018/10/29.
 */
public class MasterSelectDriver {

    static String path = "/master-select";

    public static void main(String[] args) throws Exception {
        MasterSelectDriver masterSelectDriver = new MasterSelectDriver();
        masterSelectDriver.test();

    }

    public void test() throws Exception{
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework client = getClient();
                    job(client);
                }
            });
        }
        //threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);

    }

    private CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        return client;
    }

    private void job(CuratorFramework client){
        //需要注意一定要启动客户端
        client.start();
        LeaderSelector leaderSelector = new LeaderSelector(client, path, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println(Thread.currentThread().getName() +"# 主master");
                Thread.sleep(3000);
                System.out.println("完成master，释放master权利");
            }
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                System.out.println("状态发生改变了");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
        //selectors.add(leaderSelector);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
