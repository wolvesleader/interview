package org.example.use;

import org.springframework.aop.SpringProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: BeanProcess
 *
 * @author: quincy
 * Date: 2020/10/27 上午10:33
 * History:
 */
@Configuration
public class BeanProcess implements BeanPostProcessor {
    /**
     * 该方法返回的是代理对象
     */
    @Override
    public  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //可以使用cglib的动态代理生成代理对象并且返回
        //在这里检查注解更新DataSourceConfig中属性的值
        //属于我们自己定义的DataSourceConfig bean
        if (AnnotatedElementUtils.isAnnotated(bean.getClass(),DataSource.class)){
            DataSourceConfig o = (DataSourceConfig)newInstance(bean.getClass());
            //模拟从配置中心获取到配置文件
            o.setDiverClass("dddd");
            return o;
        }

        return bean;
    }

    public <T> T newInstance(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);
        // 标识Spring-generated proxies
        enhancer.setInterfaces(new Class[]{SpringProxy.class});
        enhancer.setCallback((MethodInterceptor) (target, method, args, methodProxy) -> {
            //检测方法上是否有注解
            Key key = AnnotationUtils.findAnnotation(method, Key.class);
            if (Objects.isNull(key)) {
                return methodProxy.invokeSuper(target, args);
            }
            Method refreshMethod = ReflectionUtils.findMethod(target.getClass(), key.method());
            if (Objects.isNull(refreshMethod)) {
                return methodProxy.invokeSuper(target, args);
            }
            refreshMethod = BridgeMethodResolver.findBridgedMethod(refreshMethod);
            refreshMethod.setAccessible(true);
            refreshMethod.invoke(target, null);
            return methodProxy.invokeSuper(target, args);
        });

        T target = (T) enhancer.create();
        return target;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
