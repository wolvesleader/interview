package com.quincy.java.base.threadpool;

import java.util.concurrent.*;

/**
 * author:quincy
 * Date:2019-07-18
 * 模拟线程池中的任务达到峰值之后逐渐减小，线程池中线程数量的变化
 */
public class ThreadPoolDriver {
    public static void main(String[] args) throws Exception{

        /**                               否
         * 1.提交任务 -->判断是否达到核心线程数----创建线程执行任务
         *                                是 入队列 ---- 队列满---没有达到最大线程数---创建线程执行任务
         *                                                   -- 是--拒绝策略
         *
         *
         *                                    21
         *                                    3.01 100  3.05 1
         *                                    1.最大线程会不会回收 会回收
         *                                    2.核心线程会不会回收 不会回收
         */

        // Throwable
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,1, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(10));
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

