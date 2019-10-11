package com.quincy.java.base.hashmap;

import java.util.HashMap;

/**
 * Created by quincy on 2018/12/15.
 */
public class Demo {

    public static void main(String[] args) {

        new HashMap<String,String>();
        try {
            System.out.println("000");
        }catch (Exception e){
            System.out.println("111");
        }finally {
            System.out.println("222");
        }
    }
}
