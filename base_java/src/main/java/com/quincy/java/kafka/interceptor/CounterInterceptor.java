package com.quincy.java.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: CounterInterceptor
 *给消息在拦截器中添加序号
 * 用来检测消息是否丢失
 * @author: quincy
 * Date: 2020/12/22 下午2:44
 * History:
 */

public class CounterInterceptor implements ProducerInterceptor<String, String> {





    private AtomicInteger atomicInteger = new AtomicInteger(3);
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String lixKey =  atomicInteger.incrementAndGet() + record.key();
        //给消息添加序号，测试稳定之后可以拿掉这个Interceptor
        return new ProducerRecord<>(record.topic(),
                record.partition(),record.key(),lixKey + "," + record.value());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {




    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        System.out.println(configs.toString());
    }
}
