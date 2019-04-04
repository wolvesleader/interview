package com.quincy.java.designpatterns.singleton;

/**
 * Created by quincy on 2018/12/17.
 * 饿汉式写法
 */
public class Singleton1 {

    private Singleton1(){}

    private final  static Singleton1 singleton1 = new Singleton1();

    public static Singleton1 getSingleton1(){
        return singleton1;
    }
}
