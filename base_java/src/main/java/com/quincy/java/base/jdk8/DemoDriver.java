package com.quincy.java.base.jdk8;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by quincy on 18/5/24.
 */
public class DemoDriver {
    public static void main(String[] args) {


        ArrayList list = new ArrayList();

        ReentrantLock reentrantLock = new ReentrantLock();
        ConcurrentHashMap<String,String> hashMap = new ConcurrentHashMap<>(10);
        hashMap.put("awq","asas");
        list.stream().forEach((item)->{});


        String demoStatic = Demo.demo();
        System.out.println(demoStatic);


        DemoImplOne demoImplOne = new DemoImplOne();
        String demoDefault = demoImplOne.demoDefault();
        System.out.println(demoDefault);

    }
}
