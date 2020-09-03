package com.quincy.java.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * author:quincy
 * Date:2019-10-28
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 */
public class LeetCodeR {


    public static void main(String[] args) {

        String str  = "ababsbs";

        HashMap<String,Integer> hashMap = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            if(!hashMap.keySet().contains(String.valueOf(str.charAt(i)))){
                hashMap.put(String.valueOf(str.charAt(i)),1);
            }else{
                Integer integer = hashMap.get(String.valueOf(str.charAt(i)));
                hashMap.put(String.valueOf(str.charAt(i)),integer + 1);
            }
        }

        for (Map.Entry<String,Integer> item : hashMap.entrySet()) {
            System.out.print(item.getKey() + " " + item.getValue() + " ");
        }
    }
}
