package com.quincy.java.base.abstractconstructor;

/**
 * author:quincy
 * Date:2019-03-21
 * abstract 类构造器的作用
 * 调用抽象类的方法时候jvm会自动帮助我们创建抽象类对象
 * 有构造器的作用主要是为了让系统帮助我们创建抽象类对象
 */
public class AbstractConstructor {
    public static void main(String[] args) {
        Person man = new Man();
        man.setId("1002");
        System.out.println(man.getId());

        Man man1 = new Man();
        man1.setId("1003");
        System.out.println(man1.getId());
    }
}
