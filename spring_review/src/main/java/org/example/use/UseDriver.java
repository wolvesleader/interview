package org.example.use;

import org.example.beanlife.BeanModel;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: UseDriver
 *BeanPostProcessor自定义bean加载后属性注入
 * @author: quincy
 * Date: 2020/10/27 上午12:14
 * History:
 * 业务需求
 * 1.从配置中心获取到配置中心的配置文件中的属性
 * 2.动态的在项目中使用
 * 方案一
 * @Value("${classDriver}")
 * private String classDriver;
 * 方案二
 * 使用实体类注入
 * https://www.cnblogs.com/hujunzheng/p/9697282.html
 * https://www.it610.com/article/1282884541110829056.htm
 * https://blog.csdn.net/fzghjx/article/details/81266393
 */

public class UseDriver {
    public static void main(String[] args) {
        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext("org.example.use");
        DataSourceConfig bean = (DataSourceConfig)ctx.getBean("dataSourceConfig");
        System.out.println(bean.getDiverClass());
        ctx.close();
        System.out.println(bean);
    }
}
