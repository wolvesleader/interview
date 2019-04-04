package com.quincy.java.base.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by quincy on 18/1/8.
 */
public class UnDeadLockDriver {
    public static void main(String[] args) {

        UnDeadLockDemo task1 = new UnDeadLockDemo( true);
        UnDeadLockDemo task2 = new UnDeadLockDemo( false);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

    }
}

class UnDeadLockDemo implements Runnable{

    Lock lock=new ReentrantLock(true);//公平锁

    private boolean flag ;
    public UnDeadLockDemo( boolean flag){
        this. flag = flag;
    }

    @Override
    public void run() {
        while (true){
            if( flag){
                threadA();
            } else{
                threadB();
            }
        }
    }

    public void threadA(){

        try {

            lock.lock();
            System.out.println("线程A获取锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程A释放锁");
        }
    }

    public void threadB(){
        try {
            lock.lock();
            System.out.println("线程B获取锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("线程B释放锁");
        }
    }
}
