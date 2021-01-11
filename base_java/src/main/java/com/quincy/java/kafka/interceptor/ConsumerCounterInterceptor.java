package com.quincy.java.kafka.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: ConsumerCounterInterceptor
 *
 * @author: quincy
 * Date: 2020/12/22 下午3:08
 * History:
 */

public class ConsumerCounterInterceptor implements ConsumerInterceptor<String,String> {


    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String,String> record: records) {
            System.out.println(record.key());
            System.out.println(record.value());
        }
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
