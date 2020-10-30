package com.quincy.java.base.threadpool;

import java.util.concurrent.*;

/**
 * author:quincy
 * Date:2019-07-18
 * 模拟线程池中的任务达到峰值之后逐渐减小，线程池中线程数量的变化
 */
public class ThreadPoolDriver1 {
    public static void main(String[] args) throws Exception{


        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,100,1, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100));

        Future<?> submit = null;
         final int  result = 0;
        for (int i = 0; i < 1000; i++) {
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

            submit = executor.submit(callable);
            //get()在for循环中造成不并发执行
            Object o = submit.get();
            System.out.println();
        }


    }
}

