package com.quincy.java.base.threadpool;

import java.util.concurrent.*;

/**
 * author:quincy
 * Date:2019-07-18
 * 模拟线程池中的任务达到峰值之后逐渐减小，线程池中线程数量的变化
 */
public class ThreadPoolDriver {
    public static void main(String[] args) throws Exception{


        //Executors.newFixedThreadPool(10);

        /*
        int corePoolSize, 核心线程数
        int maximumPoolSize, 最大线程数
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue,
        ThreadFactory threadFactory,
        RejectedExecutionHandler handler
         */
        // Throwable
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,100,1, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100));
        //设置该参数峰值过后会变为1线程，不设置就是corePoolSize数量
        executor.allowCoreThreadTimeOut(true);
        Future<?> submit = null;
         final int  result = 0;
        for (int i = 0; i < 1000; i++) {
            if (i > 500){
            Thread.sleep(1000);}
            int temp = i;
            Callable<Integer> callable = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                            executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount()
                            + ";" + executor.getActiveCount()
                    );

                    return result + 1;
                }
            };
            try {

                submit = executor.submit(callable);
            } catch (Exception e) {
                //Thread.sleep(3000);

               //executor.submit(callable);
                e.printStackTrace();
            }
            //Object o = submit.get();
            //System.out.println(o);



        }

        Thread.sleep(40000);
        if (executor.getPoolSize() < 50){
            System.out.println("iiiiiii线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount()
                    + ";" + executor.getActiveCount()
            );
        }



        //executor.shutdown();

    }
}

