package com.quincy.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by quincy on 2018/10/30.
 * 发布端
 */
public class PublishEnd  {



    public static void main(String[] args) throws Exception {
        String path = "/app1/database_config";
        new PublishEnd().publish(path);
    }

    private CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.211.55.17:2181").
                sessionTimeoutMs(5000).retryPolicy(retryPolicy).build();
        return client;
    }

    //发布数据
    private void publish(String publishPath) throws Exception{
        Output output = serializableObject();
        CuratorFramework client = getClient();
        client.start();

        //判断节点是否存在
        Stat stat = client.checkExists().forPath(publishPath);
        if (stat == null){
            client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(publishPath,output.getBuffer());
        }else {
            //节点存在直接跟新数据
            client.setData().forPath(publishPath,output.getBuffer());
        }


    }

    //读取配置文件
    private DbConfig readConfig() throws Exception{
        InputStream inputStream = this.getClass().getResourceAsStream("/com/quincy/example/db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        //加载到对象中
        DbConfig dbConfig = new DbConfig();
        dbConfig.setDriverClassName(properties.getProperty("dbcp.driverclassName"));
        dbConfig.setJdbcUrl(properties.getProperty("dbcp.jdbcurl"));
        dbConfig.setUsername(properties.getProperty("dbcp.username"));
        dbConfig.setPassword(properties.getProperty("dbcp.password"));
        return  dbConfig;
    }

    private  Output serializableObject(){
        try {
            DbConfig dbConfig = readConfig();
            Kryo kryo = new Kryo();
            Output output = new Output(1,1024);
            kryo.writeObject(output,dbConfig);
            output.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
