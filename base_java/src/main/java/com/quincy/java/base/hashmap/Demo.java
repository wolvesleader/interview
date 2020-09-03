package com.quincy.java.base.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by quincy on 2018/12/15.
 */
public class Demo {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","12345");
        try {
            System.out.println("000");
        }catch (Exception e){
            System.out.println("111");
        }finally {
            System.out.println("222");
        }
        hashTest();
    }

    /**
     * 1.为什么(n - 1) & hash
     * 2.(n - 1) & hash为什么不会出现数组越界
     * hash%n
     */
    public static void hashTest(){
        //健
        String[] split = "miao".split(".");
        System.out.println(Arrays.asList(split));
        String str = "qing";

        //数组长度
        int length = 16;
        int hash = str.hashCode();
        System.out.println(hash%length);
        System.out.println((length-1)&hash);


    }
}
