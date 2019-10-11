package com.quincy.java.base.jdk8;

/**
 * Created by quincy on 18/5/24.
 */
public class DemoDriver {
    public static void main(String[] args) {
        String demoStatic = Demo.demo();
        System.out.println(demoStatic);


        DemoImplOne demoImplOne = new DemoImplOne();
        String demoDefault = demoImplOne.demoDefault();
        System.out.println(demoDefault);

    }
}
