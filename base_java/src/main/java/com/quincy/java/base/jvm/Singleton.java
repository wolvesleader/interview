package com.quincy.java.base.jvm;

/**
 * Created by quincy on 2018/12/2.
 * javac com.quincy.java.base.jvm.Singleton.java
 * java -verbose:class Singleton //输出类加载的先后顺序
 * java -verbose:class -XX:+TraceClassLoading Singleton
 */
public class Singleton {
    private Singleton(){}

    private static class LazyHolder{
        static final Singleton INSTANCE = new Singleton();
        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Object getInstance(boolean flag){
        if (flag) return new LazyHolder[2];
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance(true);
        System.out.println("-------");
        getInstance(false);
    }

}
