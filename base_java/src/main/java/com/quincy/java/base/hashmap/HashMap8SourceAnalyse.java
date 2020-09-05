package com.quincy.java.base.hashmap;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;


/**
 * Created by quincy on 2018/12/15.
 */
public class HashMap8SourceAnalyse {

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put("1","12345");
        hashMap.put("2","dd");
        hashMap.put("3","ces");
        hashMap.put("4","fff");
        hashMap.put("Aa","Aa");
        hashMap.put("BB","BB");
        hashMap.put("ABCDEa123abc","ABCDEa123abc");
        hashMap.put("ABCDFB123abc","ABCDFB123abc");
       // hashTest();
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


    public int hashCompute(Object key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void testHashCompute(){
        System.out.println(hashCompute("1"));
        System.out.println(hashCompute("2"));
        System.out.println(hashCompute("3"));
        System.out.println(hashCompute("4"));
        System.out.println(hashCompute("Aa"));
        System.out.println(hashCompute("BB"));
        System.out.println(hashCompute("ABCDEa123abc"));
        System.out.println(hashCompute("ABCDFB123abc"));

    }
}
