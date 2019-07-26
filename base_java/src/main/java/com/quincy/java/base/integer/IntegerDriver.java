package com.quincy.java.base.integer;

/**
 * author:quincy
 * Date:2019-07-26
 */
public class IntegerDriver {
    public static void main(String[] args) {
        Integer i1 = new Integer(127);
        Integer i2 = new Integer(127);
        System.out.println(i1 == i2);// false
        System.out.println(i1.equals(i2));// true
        Integer i3 = new Integer(128);
        Integer i4 = new Integer(128);
        System.out.println(i3 == i4);// false
        System.out.println(i3.equals(i4));// true

        //发生了自动装箱，执行了valueOf方法，会从缓存区获取数据
        Integer i5 = 127;
        Integer i6 = 127;
        System.out.println(i5 == i6);// true
        System.out.println(i5.equals(i6));// true
        Integer i7 = 128;
        Integer i8 = 128;
        System.out.println(i7 == i8);// false
        System.out.println(i7.equals(i8));// true
        // 要想知道为什么，就必须看源码。
        // public static Integer valueOf(int i)
        // Integer i = Integer.valueOf(100);
        // 通过查看源码我们知道如果数据在-128到127之间，是从一个缓存数组中返回的。
        // 如果不在这个范围内，就是重新创建的。

    }
}
