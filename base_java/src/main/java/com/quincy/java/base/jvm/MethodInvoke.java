package com.quincy.java.base.jvm;

/**
 * Created by quincy on 2018/12/2.
 */
public class MethodInvoke {

    public static void main(String[] args) {
        new MethodInvoke().invoke(null,1);
    }
    void invoke(Object obj ,Object...args){
        System.out.println("invoke1");
    }
    void invoke(String str,Object obj ,Object...args){
        System.out.println("invoke2");
    }
}
