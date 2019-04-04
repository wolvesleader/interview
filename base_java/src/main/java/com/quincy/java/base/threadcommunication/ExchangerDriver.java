package com.quincy.java.base.threadcommunication;

import java.util.concurrent.Exchanger;

public class ExchangerDriver {

    public void task1(Exchanger<String> exchanger){
        System.out.println("线程1开始获取任务");

        //模拟获取任务
        try {
            Thread.sleep(4000);

            //假装是获取到的任务
            String result = "123456";

            //等待比对结果
            exchanger.exchange(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void task2(Exchanger<String> exchanger){
        System.out.println("线程2开始获取任务");
        //模拟获取任务
        try {
            Thread.sleep(2000);

            //假装是获取到的任务
            String result = "123456";
            //返回比对结果
            String exchangeResult = exchanger.exchange(result);

            System.out.println("比较结果 " + exchangeResult.equals(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExchangerDriver exchangerDriver = new ExchangerDriver();
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerDriver.task1(exchanger);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                exchangerDriver.task2(exchanger);
            }
        }).start();
    }
}
