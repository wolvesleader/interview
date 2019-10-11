package com.quincy.java.designpatterns.proxy.cglib;

/**
 *     <dependency>
 *       <groupId>cglib</groupId>
 *       <artifactId>cglib</artifactId>
 *       <version>3.1</version>
 *     </dependency>
 */
public class CglibUserDao implements CglibIUserDao {
    @Override
    public void saveStr(String str) {
        System.out.println("CglibUserDao impl 1");
    }
}
