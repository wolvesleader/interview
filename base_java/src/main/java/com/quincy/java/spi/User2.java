package com.quincy.java.spi;

/**
 * author:quincy
 * Date:2019-11-21
 */
public class User2 implements Person {
    @Override
    public String getName(String name) {
        return "第二个人" + name;
    }
}
