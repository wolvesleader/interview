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
        IUserDao proxyUserDao = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("sss");

                Object invoke = method.invoke(userDao, args);


                return invoke;
            }
        });

        proxyUserDao.save("111");

    }
}
