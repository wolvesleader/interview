package com.quincy.java.base.threadcreate;

/**
 * Created by quincy on 18/1/7.
 */
public class ThreadCreateOneDriver {

    public static void main(String[] args) {


        //Thread th = new Thread();

        ThreadCreateOneDemo threadCreateOneDemo = new ThreadCreateOneDemo();
        threadCreateOneDemo.start();
    }
}


class ThreadCreateOneDemo extends Thread{

    @Override
    public void run(){

        new Exception();
        System.out.println("线程的创建方式1");
    }
}
