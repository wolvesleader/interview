package com.quincy.java.base.threadsleep;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: SleepDemoResource
 *
 * @author: quincy
 * Date: 2020/9/3 下午1:13
 * History:
 */

public class SleepDemoResource implements Runnable {
    @Override
    public void run() {
        lockTest();
    }
    public  void lockTest(){
        try {
            System.out.println("sleep 前"+Thread.currentThread().getName());
            Thread.sleep(50000);
            System.out.println("sleep 后"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
