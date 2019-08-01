package com.quincy.java.base.manythreadarraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author:quincy
 * Date:2019-08-01
 */
public class ArrayListDriver {
    public static void main(String[] args) {}


    private void arraylistTest(){
        List<String> list = new ArrayList<>();
        runThread(list);
    }

    private void synchronizedListTest(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        runThread(list);
    }

    private void copyOnWriteArrayListTest(){
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        runThread(list);
    }

    private void runThread(List list){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            },String.valueOf(i)).start();
        }
    }
}
