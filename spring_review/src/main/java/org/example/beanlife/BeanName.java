package org.example.beanlife;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: BeanName
 *
 * @author: quincy
 * Date: 2020/10/25 下午5:06
 * History:
 */
@Configuration
public class BeanName implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware-------->:" + s);
    }
}
