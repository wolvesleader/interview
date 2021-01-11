package com.quincy.java.base.hashmap;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;


/**
 * Created by quincy on 2018/12/15.
 */
public class HashMap8SourceAnalyse {


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {

       int result =  hash("null") & 15;
        System.out.println(result);


        HashMap<String, String> hashMap = new HashMap<>(4);
       // hashMap.put("1","12345");
       // hashMap.put("2","dd");
       // hashMap.put("3","ces");
       // hashMap.put("4","fff");
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
     * 3.为什么扩容后 (e.hash & oldCap) == 0
     * j + oldCap
     * 4.拆分之后的链表为什么要分为
     *  Node<K,V> loHead = null, loTail = null;Node<K,V> hiHead = null, hiTail = null;
     *  这4个节点 高位为0则相同 point = j，高位不同则 point = j + oldCap j=原来的位置
     *  假如一个hashmap由16要扩容到32
     *  扩容前 hash & (length - 1)         (oldCap & e.hash) == 0
     *  16 - 1 = 15  binary 001111        010000   位置改变 = point = j + 010000 = j + 16 = j + oldCap
     *  hash   = 48  binary 110000        110000
     *----------------------------       --------
     *                      000000        010000
     *  扩容后
     *  32 -1 = 31 binary  011111
     *  hash  = 48 binary  110000
     *----------------------------
     *                     010000   16
     *  假如一个hashmap由16要扩容到32
     *  扩容前 hash & (length - 1)
     *  16 - 1 = 15   binary 0001111      0010000      位置不变  point = j
     *  hash   = 100  binary 1100100      1100100
     *------------------------------    ----------
     *                       0000100      0000000
     *  扩容后
     *  32 -1 =  31 binary  0011111
     *  hash  = 100 binary  1100100
     *------------------------------
     *                      0000100
     *
     *
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
