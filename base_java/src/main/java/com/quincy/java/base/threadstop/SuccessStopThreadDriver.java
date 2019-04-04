package com.quincy.java.base.threadstop;

import java.util.concurrent.TimeUnit;

/**
 * Created by quincy on 18/1/8.
 * isInterrupted + 标志符来停止线程
 *
 * 1，多线程
 * 2，共享资源
 * 3，读－改－写 操作
 *
 */
public class SuccessStopThreadDriver {
    public static void main(String[] args) throws  Exception{
        SuccessStopThreadDemo successStopThreadDemo = new SuccessStopThreadDemo();
        Thread threadA = new Thread(successStopThreadDemo,"ThreadA");
        threadA.start();
        //主线程休眠一秒
        TimeUnit.SECONDS.sleep(1);
        //主线程对线程A发起终止
        threadA.interrupt();

        SuccessStopThreadDemo successStopThreadDemo1 = new SuccessStopThreadDemo();

        Thread threadB = new Thread(successStopThreadDemo1,"THreadB");
        threadB.start();

        TimeUnit.SECONDS.sleep(1);

        successStopThreadDemo1.cancel();


    }
}

class SuccessStopThreadDemo  implements Runnable{

    private volatile boolean flag = true;
    private int i;

    @Override
    public void run() {
        while (flag && !Thread.currentThread().isInterrupted()){
            i ++;
        }
        System.out.println("count i = " + i);
    }

    public void cancel(){
        flag = false;
    }
}
