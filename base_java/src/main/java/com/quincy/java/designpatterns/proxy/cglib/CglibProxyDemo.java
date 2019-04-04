package com.quincy.java.designpatterns.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/quincy/Desktop/test");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibUserDao.class);
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("intercept before");
                Object o1 = methodProxy.invokeSuper(o, objects);
                return o1;
            }
        });
        CglibUserDao proxy= (CglibUserDao)  enhancer.create();
        proxy.saveStr("ssss");
    }
}
