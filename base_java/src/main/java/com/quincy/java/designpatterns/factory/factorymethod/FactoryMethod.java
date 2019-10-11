package com.quincy.java.designpatterns.factory.factorymethod;

/**
 * author:quincy
 * Date:2019-07-07
 * 简单工厂的缺点是添加一个创建bean就需要修改工厂
 * 我们可以把工厂抽象
 * 不同的bean实现抽象工厂就可以了
 * 这样有新的bean对象出现，只要实现MethodBeanFactory
 * 接口，在自己的工厂中创建自己的对象即可
 *
 * 该方法的问题是随着bean类型的增加，需要创建的工厂也会不断增加
 * 每个工厂只能生产一类bean
 */
public class FactoryMethod {
}
