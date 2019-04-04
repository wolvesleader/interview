package com.quincy.java.base.threadcreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by quincy on 18/1/7.
 */
public class ThreadCreateThreeDriver {
    public static void main(String[] args) {

        try {
            ThreadCreateThreeDemo threadCreateThreeDemo = new ThreadCreateThreeDemo();

            FutureTask<Integer> futureTask = new FutureTask<Integer>(threadCreateThreeDemo);

            new Thread(futureTask).start();

            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }
    }


}

class ThreadCreateThreeDemo implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {

        return 12;
    }
}
