package com.quincy.java.base.hashmap;


import org.junit.Test;

import java.util.*;


/**
 * Created by quincy on 2018/12/15.
 */
public class HashMap8SourceAnalyse {


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {

        System.out.println(12 & 16);;
        System.out.println(12 & (16 - 1));

        int result =  hash("null") & 15;
        System.out.println(result);


        HashMap<String, String> hashMap = new HashMap<>();
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
     *  16 - 1 = 15  binary 00 1111               位置改变 = point = j + 010000 = j + 16 = j + oldCap
     *  hash   = 48  binary 11 0000
     *----------------------------
     *                      00 0000
     *  扩容后
     *  32 -1 = 31 binary  01 1111        0010000
     *  hash  = 48 binary  11 0000        0110000
     *----------------------------      ----------
     *                     01 0000   16   0010000
     *
     *
     *
     *  假如一个hashmap由16要扩容到32
     *  扩容前 hash & (length - 1)
     *  16 - 1 = 15   binary 000 1111            位置不变  point = j
     *  hash   = 100  binary 110 0100
     *------------------------------
     *                       000 0100
     *  扩容后
     *  32 -1 =  31 binary  001 1111      0010000
     *  hash  = 100 binary  110 0100      1100100
     *------------------------------   -------------
     *                      000 0100      0000000
     *
     *hash & ( length -1 ) 和 (oldCap & e.hash)的关系
     * HashMap 的扩容都是扩大为原来大小的两倍，从二进制上看就是给这串数字加个 0，
     * 比如 16 -> 32 = 10000 -> 100000，那么他的 n - 1 就是 15 -> 32 = 1111 -> 11111。
     * 也就是多了一位，所以扩容后的下标可以从原有的下标推算出来。差异就在于上图我标红的地方，
     * 如果标红处是 0，那么扩容后再求余结果不变，如果标红处是 1，那么扩容后再求余就为原索引 + 原偏移量。
     * 如何判断标红处是 0 还是 1，就是把 e.hash & oldCap
     *
     * 扩容前和扩容后只差了高位 的第一个位置
     * 所以我们只要计算出高位的第一个位置的值是 1 还是 0 就可以了
     * 如果是0 位置不变
     * 如果是1 位置变为原来的 位置 + oldCap
     * 计算高位的方法就是e.hash & oldCap
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
