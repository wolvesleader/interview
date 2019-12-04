package com.quincy.java.spi;

/**
 * author:quincy
 * Date:2019-11-21
 */
public class User1 implements Person {
    @Override
    public String getName(String name) {
        System.out.println("dd");
        return "第一个人" + name;
    }
}
