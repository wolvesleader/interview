package com.quincy.java.algorithm.leetcode;

/**
 * author:quincy
 * Date:2019-10-28
 */
public class LeetCode2 {

    public static void main(String[] args) {
        String s = "abcabcabc";
        int n = s.length(), ans = 0;
        int[] index = new int[128];
        //[i,j]窗口思想
        for (int i = 0,j = 0; i < n; i++) {
           j = Math.max(index[s.charAt(i)],j);
           ans = Math.max(ans,j - i + 1);
            index[s.charAt(i)] = i + 1;
        }

        System.out.println(ans);

    }
}
