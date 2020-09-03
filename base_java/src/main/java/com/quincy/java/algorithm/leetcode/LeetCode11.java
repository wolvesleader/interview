package com.quincy.java.algorithm.leetcode;

/**
 * author:quincy
 * Date:2019-11-09
 */
public class LeetCode11 {
    public static void main(String[] args) {
         LeetCode11 leetCode11 = new LeetCode11();

         int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(leetCode11.maxArea(arr));
    }

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l=0 ,r=height.length - 1 ;
        int maxArea = 0;
        while (l < r){
           int minY = Math.min(height[l],height[r]) ;
           maxArea = Math.max(maxArea,minY * (r - l));
           if (minY == height[l]){
               l ++;
           }
           else if(minY == height[r]){
               r --;
           }
           else {
               l ++;
               r --;
           }
        }
        return maxArea;
    }
}
