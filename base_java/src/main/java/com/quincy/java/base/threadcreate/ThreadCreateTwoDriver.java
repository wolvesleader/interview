package com.quincy.java.base.threadcreate;

/**
 * Created by quincy on 18/1/7.
 */
public class ThreadCreateTwoDriver {
    public static void main(String[] args) {
        ThreadCreateTwoDemo threadCreateTwoDemo = new ThreadCreateTwoDemo();
        Thread th = new Thread(threadCreateTwoDemo);
        th.start();
    }
}

class ThreadCreateTwoDemo implements Runnable{

    @Override
    public void run()  {

        System.out.println("ddddd&&&&");
    }
}
