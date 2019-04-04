package com.quincy.java.base.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincy on 18/1/16.
 */
public class Neicunfenpei {

    public static void main(String[] args) {


        byte[] b1,b2,b3,b4;

//        b1= new byte[1024*640];
//        b2 = new byte[1024*640];
//        b3 = new byte[1024*1024*4];
//        b3 = null;
//        b3 = new byte[1024*1024*4];


        //System.gc();
        List<String> strs = new ArrayList<String>();
        int i = 0;

        while(true) {
            strs.add(String.valueOf(i++).intern());
            System.out.println("We have created " + i + " constant String.");
        }

    }


    public static void test(){

    }
}
