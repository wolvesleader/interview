package com.quincy.java.base.threadcreate;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * author:quincy
 * Date:2019-04-21
 * 洗水壶->烧开水->
 * 洗茶壶->洗茶杯->拿茶叶
 *
 */
public class FutureDemo {
    public static void main(String[] args) throws Exception{



        FutureTask<String> futureTask2 = new FutureTask(new Task2());

        Thread thread = new Thread(futureTask2);
        thread.start();

        FutureTask<String> futureTask1 = new FutureTask(new Task1(futureTask2));
        Thread thread1 = new Thread(futureTask1);
        thread1.start();

        String result = futureTask1.get();
        System.out.println(result);

    }

}

class Task1 implements Callable<String> {
    FutureTask<String> futureTask;

    public Task1(FutureTask<String> futureTask){
        this.futureTask = futureTask;
    }

    @Override
    public String call() throws Exception {
        System.out.println("洗水壶");
        System.out.println("烧开水");
        String result = futureTask.get();

        System.out.println("拿到茶叶 " + result);

        System.out.println("泡茶....");

        return "上茶 " + result;
    }
}

class Task2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("洗茶壶");
        System.out.println("洗茶杯");
        System.out.println("拿茶叶");
        return "龙井茶好了";
    }
}