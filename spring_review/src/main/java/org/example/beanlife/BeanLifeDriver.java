package org.example.beanlife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: BeanLifeDriver
 *
 * @author: quincy
 * Date: 2020/10/23 下午3:52
 * History:
 * -->bean实例化
 * -->实现BeanNameAware接口走setBeanName
 * -->实现BeanFactoryAware接口走setBeanFactory
 * -->实现BeanPostProcessorj接口走postProcessBeforeInitialization()
 *bean生命周期在项目中的应用
 *
 */

public class BeanLifeDriver {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        //AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext("com.quincy.java.spring.beanlife");
        BeanModel bean = (BeanModel)ctx.getBean("beanModel");
        ctx.close();
        System.out.println(bean);
    }
}
