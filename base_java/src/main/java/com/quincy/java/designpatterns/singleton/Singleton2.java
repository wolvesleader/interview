package com.quincy.java.designpatterns.singleton;

/**
 * Created by quincy on 2018/12/20.
 */
public class Singleton2 {
    private Singleton2(){
    }

    /*懒汉模式*/
    private static volatile Singleton2 s = null;

    public static  Singleton2 getSingleTon(){

        if( s == null){
            //位置1
            synchronized (Singleton2. class) {
                      /*判断没有对象创建的时候，就去创建*/
                      /*如果删除里边这一层判断
                      * 当第一个线程进入第一个if判断快以后，假如他停在了位置1处
                      * 当第二个线程进入以后，在第一个线程new完对象之后，第二个线程任然会new一个对象
                      *
                      * 加上第二个if判断之后，在第一个线程new完之后，第二个线程进入发现已经有对象了，
                      * 就不会再去new对象了
                      * */
                if( s == null){
                    s = new Singleton2();
                }
            }
        }
        return s;
    }
}
