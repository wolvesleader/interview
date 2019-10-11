package com.quincy.java.base.deadlock;

/**
 * Created by quincy on 18/1/8.
 */
public class DeadLockDriver {

    public static void main(String[] args) {

        DeadLockDemo task1 = new DeadLockDemo( true);
        DeadLockDemo task2 = new DeadLockDemo( false);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);



        t1.start();
        t2.start();
    }
}

class DeadLockDemo  implements Runnable{
    private boolean flag ;
    public DeadLockDemo( boolean flag){
        this. flag = flag;
    }

    @Override
    public void run() {
        if( flag){
            synchronized (MyLock. LOCKA) {
                System. out.println( "LOCKA=====>LOCKB");
                synchronized (MyLock. LOCKB) {
                    System. out.println( "LOCKA=LOCKB");
                }
            }
        } else{
            synchronized (MyLock. LOCKB) {
                System. out.println( "LOCKB=====>LOCKA");
                synchronized (MyLock. LOCKA) {
                    System. out.println( "LOCKB=LOCKA");
                }
            }
        }
    }
}

class MyLock{
    public  static final Object LOCKA = new Object();
    public static final Object LOCKB = new Object();
}
