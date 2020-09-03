package com.quincy.java.base.threadsleep;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: SleepDemo
 *
 * @author: quincy
 * Date: 2020/9/3 下午1:11
 * History:
 * Thread.sleep 释放CPU的执行权，不会释放锁
 * 1.Thread.sleep持有的锁对象是谁
 * 2.怎么查看到Thread.sleep持有的锁对象 自身没有锁对象，不释放锁指的是不释放synchronized 等其他的锁对象
 * 3.测试Thread.sleep不会释放锁案例
 * jstack -l 14409 >jvm_listlocks_lock.txt
 * 堆栈信息分析网站
 * https://fastthread.io/
 */

public class SleepDemo {
    public  static void main(String[] args) {

        SleepDemoResource sleepDemoResource = new SleepDemoResource();
        new Thread(sleepDemoResource).start();

        new Thread(sleepDemoResource).start();

        new Thread(sleepDemoResource).start();


        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ddddd");
    }
}
