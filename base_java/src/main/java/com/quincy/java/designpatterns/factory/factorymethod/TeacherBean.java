package com.quincy.java.designpatterns.factory.factorymethod;

/**
 * author:quincy
 * Date:2019-07-07
 */
public class TeacherBean implements MethodBeanFactory {
    @Override
    public Bean createBean() {
        return new Teacher();
    }
}
