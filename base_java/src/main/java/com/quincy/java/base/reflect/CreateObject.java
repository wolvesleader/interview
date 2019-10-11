package com.quincy.java.base.reflect;

import java.lang.reflect.Constructor;

/**
 * author:quincy
 * Date:2019-08-19
 */
public class CreateObject {

    public static void main(String[] args) {
        try {
            Class<Object> objectClass = Object.class;
            Object o = objectClass.newInstance();
            Constructor<Object> constructor = objectClass.getConstructor(null);
            Object o1 = constructor.newInstance(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
