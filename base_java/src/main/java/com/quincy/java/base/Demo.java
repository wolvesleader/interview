package com.quincy.java.base;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: Demo
 *
 * @author: quincy
 * Date: 2020/11/16 下午5:11
 * History:
 */

public class Demo {
    public static void main(String[] args) {

        synchronized (Demo.class){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
