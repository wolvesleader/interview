package org.example.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: ZBeanFactory
 *
 * @author: quincy
 * Date: 2020/10/25 下午5:06
 * History:
 */
@Configuration
public class ZBeanFactory implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("对象---" + beanName + "---初始化开始");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("对象---" + beanName + "---初始化成功");
        return bean;
    }
}
