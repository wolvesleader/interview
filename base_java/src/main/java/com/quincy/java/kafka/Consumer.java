package com.quincy.java.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Consumer
 *
 * @author: quincy
 * Date: 2020/12/5 下午8:14
 * History:
 */

public class Consumer {
    public static void main(String[] args) throws Exception{
        consumerGroup();
        //Thread.sleep(10000000);
    }

    private static void consumerSimple(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"CentOS7mini");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singletonList("kafka_info"));

        ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord record: records) {
            System.out.println(record.key());
            System.out.println(record.value());
        }
    }

    /**
     * 模拟一个消费组
     * 在同一个消费组里面，每个队列只能被一个消费者实例占用
     */
    private static void consumerGroup(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"CentOS7mini");
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
                    kafkaConsumer.subscribe(Collections.singletonList(Producer.class.getCanonicalName()));
                    ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                    for (ConsumerRecord record: records) {
                        System.out.println(Thread.currentThread().getName() + ":" +record.key() + "=" + record.value());
                    }
                    kafkaConsumer.close();
                }
            }).start();
        }
    }

    /**
     * 消息在三个分区，消费的时候有时候回出现顺序不一一致
     * 这个问题就是消息队列的无序性
     */

    @Test
    public   void unorderConsumer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"CentOS7mini");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singletonList("test1"));

        while (true){
            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofMillis(6000));
            for (ConsumerRecord record: records) {
                System.out.println(record.key());
                System.out.println(record.value());
            }
        }
    }

    @Test
    public   void orderConsumer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.4:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"CentOS7mini");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singletonList("test1"));

        //while (true){
            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofMillis(6000));
            for (ConsumerRecord record: records) {
                System.out.println(record.key());
                System.out.println(record.value());
            }
        //}
    }
}
