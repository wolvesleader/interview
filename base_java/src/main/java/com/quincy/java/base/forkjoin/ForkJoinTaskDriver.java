package com.quincy.java.base.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class ForkJoinTaskDriver extends RecursiveTask<Long> {

    private long start;
    private long end;

    public ForkJoinTaskDriver(long start,long end) {
        this.start = start;
        this.end = end;
    }



    @Override
    protected Long compute() {

        long sum = 0;

        //如果任务太小不需要拆分，直接计算
        if (end - start <= 1000000000L){
            for (long s=start;s <= end ; s ++){
                sum += s;
            }
            return sum;
        }else{
            //任务太大需要拆分
            //我们每次拆分为2半
            long midle = (start+end) >>> 1;
            ForkJoinTaskDriver t1 = new ForkJoinTaskDriver(start,midle);
            t1.fork();
            ForkJoinTaskDriver t2 = new ForkJoinTaskDriver(midle + 1,end);
            //计算
            t2.fork();
            //合并
            //long result1 = t1.join();
            //long result2 = t2.join();

            //sum = result1 + result2;
            return t1.join() + t2.join();
        }


        //return sum;
    }

    public static void main(String[] args) throws Exception{

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Long> task = forkJoinPool.submit(new ForkJoinTaskDriver(0L,100000000000L));
        //计算的结果值
        long srarttime = System.currentTimeMillis();
        long integer = task.get();
        long enttime = System.currentTimeMillis();
        System.out.println("消耗时间： " +(enttime-srarttime)+ " 结果值 ：" + integer);

    }
}
