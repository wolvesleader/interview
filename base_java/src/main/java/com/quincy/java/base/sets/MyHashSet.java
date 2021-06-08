package com.quincy.java.base.sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by quincy on 18/5/5.
 * 分析set集合保证唯一性的原理
 */
public class MyHashSet {

    public static void main(String[] args) {
        HashSet<String> sets = new HashSet<>();
        sets.add("11");
        sets.add("11");
        HashMap<String,String> hashMap = new HashMap<>();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("33","44");
        int[] arr = new int[10];

        hashMap.put("","");

        //sets.contains("sss");

    }
}
