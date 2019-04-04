package com.quincy.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by quincy on 2018/10/30.
 * 订阅端
 */
public class SubEnd {

    public static void main(String[] args) throws Exception{
        String path = "/app1/database_config";
        new SubEnd().getDataFromZookeeperNode(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        return client;
    }

    private void getDataFromZookeeperNode(final String path) throws  Exception{
       final CuratorFramework client = getClient();
       client.start();
        //订阅端需要设置监听器，如果发布端发生了改变，需要做处理
        NodeCache nodeCache = new NodeCache(client,path,false);

        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                //数据发生变化获取到之后写入到本地
                System.out.println("数据节点发生变化");
                unSerializableObject(client,path);
            }
        });

        unSerializableObject(client,path);
    }

    //反序列化对像
    private void unSerializableObject(CuratorFramework client,String path) throws Exception{
        byte[] bytes = client.getData().forPath(path);
        Kryo kryo = new Kryo();
        Input input = new Input(bytes);
        DbConfig dbConfig = kryo.readObject(input, DbConfig.class);
        input.close();

        writeConfig(dbConfig);
    }

    //写出配置文件  change.properties
    private void writeConfig(DbConfig dbConfig) throws Exception{
        String path = this.getClass().getResource("/com/quincy/example/change.properties").getPath();
        OutputStream os = new FileOutputStream(new File(path));
        Properties properties = new Properties();
        properties.setProperty("dbcp.driverclassName",dbConfig.getDriverClassName());
        properties.setProperty("dbcp.jdbcurl",dbConfig.getJdbcUrl());
        properties.setProperty("dbcp.username",dbConfig.getUsername());
        properties.setProperty("dbcp.password",dbConfig.getPassword());
        properties.store(os,null);
        os.close();
    }
}
