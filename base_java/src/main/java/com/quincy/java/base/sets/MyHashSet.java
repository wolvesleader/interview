package com.quincy.java.base.sets;

import java.util.HashSet;

/**
 * Created by quincy on 18/5/5.
 * 分析set集合保证唯一性的原理
 */
public class MyHashSet {

    public static void main(String[] args) {
        HashSet<String> sets = new HashSet<>();
        sets.add("11");
        sets.add("11");

        //sets.contains("sss");

    }
}
