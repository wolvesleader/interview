package com.quincy.java.base.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by quincy on 18/1/27.
 */
public class DemoDriver {

    private int a = 10;



    public static void main(String[] args) {

     //   new DemoDriver().a;
        ExecutorService executorService = Executors.newCachedThreadPool();

        //synchronized (DemoDriver.class) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }




    }

}
