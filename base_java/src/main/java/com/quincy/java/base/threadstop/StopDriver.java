package com.quincy.java.base.threadstop;

/**
 * Created by quincy on 18/1/7.
 * 如果看的还不是很明白可以参考以下这一片帖子
 * http://yeziwang.iteye.com/blog/844649
 */
public class StopDriver {

    public static void main(String[] args) {
        StopDemo ad = new StopDemo();

       Thread threadA = new Thread(ad);
       threadA.start();

       Thread threadB = new Thread(ad);

       threadB.start();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.stop();
        System.out.println("线程A停止");
    }
}

class StopDemo implements  Runnable{

    private  Integer userA = 100;
    private  Integer userB = 0;

    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                userA = userA - 1;

                try {
                    // 线程休眠0.1秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userB = userB + 1;
                String tnn = Thread.currentThread().getName();
                System.out.println(tnn + "userA =" + userA + ":userB =" + userB);
            }
        }
    }



}
