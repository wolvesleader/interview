package com.quincy.java.designpatterns.builder;

/**
 * author:quincy
 * Date:2019-05-18
 * 建造者模式
 * 主要的作用是
 * 对象的创建和对象的组装分离
 */
public class BuilderPattern {

    public static void main(String[] args) {
        User user = new User.Builder().addId("1002").addSex("男").addUsername("大熊").build();
        System.out.println(user.getId());
    }


}
