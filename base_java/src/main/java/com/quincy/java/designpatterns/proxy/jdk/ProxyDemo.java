package com.quincy.java.designpatterns.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by quincy on 18/6/16.
 */
public class ProxyDemo {
    public static void main(String[] args) {

        IUserDao userDao = new UserDao();
        //1.JDK动态代理为什么只能对接口进行代理
        //2.为什么要传入被代理对象的类加载器
        //3.为什么需要传入被代理对象实现的所有接口
        //4.InvocationHandler是个什么东西
        IUserDao proxyUserDao = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("sss");


                //执行被代理对象里边的save方法
                Object invoke = method.invoke(userDao, args);



                return invoke;
            }
        });

        proxyUserDao.save("111");

    }
}
