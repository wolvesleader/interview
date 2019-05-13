package com.quincy.java.redis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * author:quincy
 * Date:2019-05-13
 *
 */
public class RedisBloomFilterPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.endsWith("redisBloomFilter")){
            RedisBloomFilter redisBloomFilter = (RedisBloomFilter) bean;
            //需要预热到BloomFilter中的数据,真实的环境下需要替换
            String[] strs = {"1","2","3","4","5"};
            for (String str : strs) {
                redisBloomFilter.put(str);
            }
        }
        return bean;
    }
}
