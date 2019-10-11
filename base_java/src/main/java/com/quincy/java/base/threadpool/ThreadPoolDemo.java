package com.quincy.java.base.threadpool;

import java.util.concurrent.Executor;

/**
 * Created by quincy on 18/1/4.
 *
 * 线程池的创建和原理
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //Thread
        ThreadLocal<String > th = new ThreadLocal<String>();
        th.set("value");

        ThreadResources resources = new ThreadResources();

//        MyThreadPool myThreadPool = new MyThreadPool();
//        //resources.run();
//        System.out.println("----------------");
//        //resources.run();
//        myThreadPool.execute(resources);//这和直接调用资源的run方法没有任何的区别啊
//        myThreadPool.execute(resources);

        //Executors.newFixedThreadPool(5);
        //创建固定线程的线程池
//        ExecutorService executorService =
//                Executors.newFixedThreadPool(5);
//
//        executorService.execute(new ThreadResources());
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0 ; i < 1000000000; i ++){
//                    System.out.println(Thread.currentThread().getName() + "---" + i);
//                }
//            }
//        });
    }
}

class ThreadResources  implements  Runnable{
    private int a = 100;

    @Override
    public void run() {
        for (int i = 0; i < 100; i ++){
            System.out.println(Thread.currentThread().getName() + "&&&&&" + (a --));
        }
    }
}

/**
 *
 * 我们可以不开线程，通过执行器来执行任务
 * 任务和执行器分离通过执行器来执行任务
 */
class MyThreadPool implements Executor{
    @Override
    public void execute(Runnable command) {

          command.run();

    }
}

/**
 * 执行器执行任务的时候，可以在另外一个线程中执行
 */
class MyThreadPool2 implements Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}


