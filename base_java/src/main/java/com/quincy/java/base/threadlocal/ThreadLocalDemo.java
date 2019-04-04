package com.quincy.java.base.threadlocal;

/**
 * Created by quincy on 18/1/5.
 */
public class ThreadLocalDemo {


    public static final ThreadLocal<Integer> thInteger = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 10;
        }
    };

    public static void main(String[] args) {

        ThreadLocalResource threadLocalResource = new ThreadLocalResource(thInteger);
        for (int i = 0; i < 5;i ++){
            new Thread(threadLocalResource).start();
        }
    }
}

class ThreadLocalResource implements Runnable{
    private ThreadLocal<Integer> thInteger;

    public ThreadLocalResource(ThreadLocal<Integer> thInteger){
        this.thInteger = thInteger;
    }
    @Override
    public void run() {
            try {
                //if(thInteger.get() == null){
                //    thInteger.set(10);
                //}else{
                   while(thInteger.get() >= 0){
                        System.out.println(Thread.currentThread().getName() + " === " + thInteger.get() );
                       Integer temp = thInteger.get();
                        thInteger.set(temp - 1);
                    }
               // }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //thInteger.remove();
            }

    }
}
