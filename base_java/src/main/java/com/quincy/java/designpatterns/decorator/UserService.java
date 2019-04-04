package com.quincy.java.designpatterns.decorator;

/**
 * Created by quincy on 2018/12/20.
 * 装饰者设计模式主要是对原有类的增强
 * 应为在开发中有的时候我们不能修改原有类的代码，
 * 只能通过增强的方式来实现
 */
public class UserService {

    public void save(){
        System.out.println("原来的方法");
    }
}
