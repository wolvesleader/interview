package com.quincy.java.designpatterns.template;

/**
 * author:quincy
 * Date:2019-06-06
 * 模版方法模式
 */
public class TemplateDriver {
    public static void main(String[] args) {
        BaseDao userDao = new UserDao();
        System.out.println(userDao.getById());
        BaseDao orderDao = new OrderDao();
        System.out.println(orderDao.getById());
    }
}
