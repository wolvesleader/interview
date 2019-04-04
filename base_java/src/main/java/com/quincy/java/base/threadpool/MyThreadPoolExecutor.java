package com.quincy.java.base.threadpool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by quincy on 18/1/4.
 */
public class MyThreadPoolExecutor {

    //存放任务的队列
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(100);

    private final HashSet<Worker> workers = new HashSet<Worker>();

    private final List<Thread> threads = new ArrayList<Thread>();

    private volatile boolean isRunning = true;

    private AtomicInteger coreSize = new AtomicInteger(0);

    private  AtomicInteger initSize ;


    private AtomicInteger maxPoolSize ;

    public MyThreadPoolExecutor(int coreSize,int maxPoolSize){
        

        this.initSize = new AtomicInteger(coreSize);
        System.out.println(this.coreSize.get());
        this.maxPoolSize = new AtomicInteger(maxPoolSize);
    }


    //执行器，开始执行任务
    public void executor(Runnable runnable){
        try {
            if(coreSize.get() <  initSize.get() ){
                 addWorker(runnable);
            }else{
                //创建的线程数量已尽达到最大数量，把任务存放在队列中
                workQueue.put(runnable);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    //添加工作线程
    private void addWorker(Runnable runnable){

        coreSize.getAndIncrement();//值加1
        //创建线程
        Worker worker = new Worker(runnable);
        workers.add(worker);
        Thread th = new Thread(worker);
        threads.add(th);

        th.start();

    }


    //内部的工作线程
    private class Worker implements  Runnable{

        private Thread t ;
        private Runnable firstTask;

        public  Worker(Runnable firstTask){
            this.firstTask = firstTask;
        }
        @Override
        public void run() {
            //从任务队列中获取到任务，并且执行的操作
            runWorker(this);
        }
    }

    private void runWorker(Worker worker){

        while(true){
            try {
                Runnable task = getTask();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    //获取到任务
    private Runnable getTask() throws InterruptedException{
        return workQueue.take();
    }


}
