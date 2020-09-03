package com.quincy.java.algorithm.leetcode;

/**
 * Copyright (C), 2015-2020, 大众易书天津科技有限公司
 * FileName: LeetCode28
 *
 * @author: quincy
 * Date: 2020/7/22 下午6:12
 * History:
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1
 */

public class LeetCode28 {
    public static void main(String[] args) {
        System.out.println(strStr("phello", "llo"));
    }
    public static int strStr(String haystack, String needle) {
        int position = -1;
        int index = 0;
        int indexN = 0;
        while (haystack.length() > index){
            if (indexN < needle.length() && haystack.charAt(index) == needle.charAt(indexN)){
                indexN ++;
                position ++;
            }
            if (indexN == needle.length()){
                //position = haystack.length() - position - 1;
                break;
            }
            index ++;
        }

        return position;
    }
}
