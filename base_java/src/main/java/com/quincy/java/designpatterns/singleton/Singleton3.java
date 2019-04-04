package com.quincy.java.designpatterns.singleton;

/**
 * Created by quincy on 2018/12/17.
 * 延迟加载线程安全写法
 */
public class Singleton3 {

    private Singleton3(){}

    private static class Single3Holder{
        private final  static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getSingleton3(){
        return Single3Holder.singleton3;
    }
}

