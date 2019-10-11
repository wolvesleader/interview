package com.quincy.java.base.jdk8;

/**
 * Created by quincy on 18/5/24.
 非default、static方法不能有实现，否则编译错误：Abstract methods do not specify a body
 default、static方法必须有具体的实现，否则编译错误：This method requires a body instead of a semicolon
 可以拥有多个default方法
 可以拥有多个static方法
 使用接口中类型时，仅仅需要实现抽象方法，default、static方法不需要强制自己新实现
 */
public interface Demo {


    public void implOne();

    default void test(){

    }


    /**
     * 方法的定义
     */
    public static String demo(){
        return "interface 方法体 方法被static修饰";
    }


    /**
     * 存在的作用，或者设计的原则，不修改其他已经实现了该接口的类的前提下，给接口添加新的方法
     * 或者接口方法有一个默认的实现
     * @return
     */
    default String demoDefault(){
        return "interface 方法提 方法被default修饰";
    }
}
