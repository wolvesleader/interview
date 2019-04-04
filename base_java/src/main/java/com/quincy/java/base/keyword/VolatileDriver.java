package com.quincy.java.base.keyword;

/**
 * Created by quincy on 18/1/7.
 * volatile为什么能保证线程之间变量的可见性
 * http://ifeve.com/volatile/
 * 解决方法一:添加同步锁
 * 解决方法二:使用volatile关键字
 *
 * 存在线程安全的原因
 * 1，多条线程
 * 2，共享资源
 */
public class VolatileDriver {
    public static void main(String[] args) {
        VolatileDemo td = new VolatileDemo();
        new Thread(td).start();
        while(true){
            //System.out.println("-----");
            // synchronized (VolatileDriver.class){
            if(td.isFlag()){
                System.out.println("---&&&&--");
                break;
            }
            //}
            //System.out.println("-----");
        }
    }
}



class VolatileDemo implements Runnable{
    private  boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
