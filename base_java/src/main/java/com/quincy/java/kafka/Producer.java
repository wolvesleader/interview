package com.quincy.java.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Producer
 *
 * @author: quincy
 * Date: 2020/12/5 下午7:45
 * History:
 */

public class Producer {
    public static void main(String[] args) {
       thread();
    }

    private static void simple(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        //2个分区，相当于存在2个队列
        ProducerRecord producerRecord = new ProducerRecord("kafka_info",2,"ererer","miao");
        kafkaProducer.send(producerRecord);
        kafkaProducer.close();
    }

    private static void thread(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //2个分区，相当于存在2个队列
        for (int i = 0; i < 300; i++) {
            int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    KafkaProducer kafkaProducer = new KafkaProducer(properties);
                    //不指定分区是发送到哪个分区上的
                    //生成一个随机数字
                    ProducerRecord producerRecord = new ProducerRecord(Producer.class.getCanonicalName(),String.valueOf(System.currentTimeMillis()),"dd".concat(String.valueOf(temp)));
                    kafkaProducer.send(producerRecord);
                    kafkaProducer.close();
                }
            }).start();
        }
    }

    /**
     * 消息顺序性演示
     * 不同分区 ，单个消费者获取消息的顺序 会不确定
     * 创建了一个test topic 有2个分区，相当于3个队列
     * 会给三个分区分别发送一个消息
     */
    @Test
    public   void unorderProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        //2个分区，相当于存在2个队列
        String orderId = System.currentTimeMillis() + "";
//        // 发送insertmsg
        String insert = getSqlMsg("insert", orderId);
        // 发送Updatemsg
        String update = getSqlMsg("update", orderId);
        // 发送deletemsg
        String delete = getSqlMsg("delete", orderId);

        ProducerRecord producerRecord = new ProducerRecord("test1",insert);
        kafkaProducer.send(producerRecord);

        ProducerRecord producerRecord1 = new ProducerRecord("test1",update);
        kafkaProducer.send(producerRecord1);

        ProducerRecord producerRecord2 = new ProducerRecord("test1",delete);
        kafkaProducer.send(producerRecord2);

        kafkaProducer.close();
    }

    public static String getSqlMsg(String type, String orderId) {
        JSONObject dataObject = new JSONObject();
        dataObject.put("type", type);
        dataObject.put("orderId", orderId);
        return dataObject.toJSONString();
    }

    /**
     * 局部有序消费消息
     */
    @Test
    public void orderProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        //2个分区，相当于存在2个队列
        String orderId = System.currentTimeMillis() + "";
//        // 发送insertmsg
        String insert = getSqlMsg("insert", orderId);
        // 发送Updatemsg
        String update = getSqlMsg("update", orderId);
        // 发送deletemsg
        String delete = getSqlMsg("delete", orderId);

        ProducerRecord producerRecord = new ProducerRecord("test1",orderId,insert);
        kafkaProducer.send(producerRecord);

        ProducerRecord producerRecord1 = new ProducerRecord("test1",orderId,update);
        kafkaProducer.send(producerRecord1);

        ProducerRecord producerRecord2 = new ProducerRecord("test1",orderId,delete);
        kafkaProducer.send(producerRecord2);

        kafkaProducer.close();
    }

}
