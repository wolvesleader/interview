package org.example.beanlife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: BeanModel
 *
 * @author: quincy
 * Date: 2020/10/23 下午3:51
 * History:
 */
@Component("beanModel")
public class BeanModel {

    @Autowired
    private Bean bean;

    public BeanModel() {
        System.out.println("第一步：初始化出来对象 " + bean);
    }


}
