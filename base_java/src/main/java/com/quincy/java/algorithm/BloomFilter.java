package com.quincy.java.algorithm;

/**
 * byte类型的数组实现
 * author:quincy
 * Date:2019-03-17
 */
public class BloomFilter {

    public static void main(String[] args) {
        int i = 3156;

        int[] arr = new int[100];

        arr[i / 32] |= (1 << (i % 32));

        int result = arr[i / 32] & (int)(1 << (i % 32));
        System.out.println(result);
    }
}
