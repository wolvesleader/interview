package com.quincy.java.designpatterns.factory.simplefactory;

import org.springframework.util.StringUtils;

/**
 * author:quincy
 * Date:2019-07-07
 * 创建bean对象
 * 简单工厂的缺点,如果我需要创建不同的bean对象
 * 就需要不断的修改我们的BeanFactory类
 * 违反了开闭原则
 * 只适用于创建少量的，变动不频繁的对象
 */
public class BeanFactory {
    /**
     * 根据传入的参数，创建不同的bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){

//        if (StringUtils.isEmpty(beanName)){
//            return null;
//        }
        if ("user".equals(beanName)){
           return new SimpleUser();
        }else if ("person".equals(beanName)){
            return new SimplePerson();
        }
        return null;
    }

}
