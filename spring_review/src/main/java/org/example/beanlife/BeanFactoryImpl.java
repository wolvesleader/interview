package org.example.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: BeanFactoryImpl
 *
 * @author: quincy
 * Date: 2020/10/25 下午5:05
 * History:
 */
@Configuration
public class BeanFactoryImpl implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware------->" + beanFactory);
    }
}
