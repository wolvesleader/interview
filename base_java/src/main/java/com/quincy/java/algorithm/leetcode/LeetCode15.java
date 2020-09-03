package com.quincy.java.algorithm.leetcode;

import java.util.*;

/**
 * author:quincy
 * Date:2019-11-12
 */
public class LeetCode15 {

    public static void main(String[] args) {
       LeetCode15 leetCode15 = new LeetCode15();
       int[] nums = {-1, 0, 1, 2, -1, -4};
        //int[] nums = {0, 0, 0};
       // int[] nums = {0,0,0,0};
        System.out.println(leetCode15.threeSum(nums).toString());
    }


    public List<List<Integer>> threeSum(int[] nums) {
        ///排序
        Arrays.sort(nums);
        int l;
        int r;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
           l = i + 1;
           r = nums.length  - 1;
           if (i > 0 && nums[i] == nums[i - 1]) continue;
           while (l < r){
               if (nums[i] + nums[l] + nums[r] == 0){
                   //找到了一组数据
                   List<Integer> temp = new ArrayList<>();
                   temp.add(nums[i]);
                   temp.add(nums[l]);
                   temp.add(nums[r]);
                   lists.add(temp);
                   //去掉左边重复的数字
                   while (l < r && nums[l] == nums[l + 1]){
                      l++;
                   }
                   //去掉右边重复的数字
                   while (l < r && nums[r] == nums[r - 1]){
                       r--;
                   }
                   l ++;
                   r --;
               }
               else if(nums[i] + nums[l] + nums[r] > 0){
                   r --;
               }
               else {
                  l ++;
               }

           }
        }
        return lists;
    }
}
