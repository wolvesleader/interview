package com.quincy.java.algorithm.orphanage;

import java.util.Arrays;

/**
 * author:quincy
 * Date:2019-11-12
 */
public class LeetCode16 {

    public static void main(String[] args) {
       LeetCode16 leetCode16 = new LeetCode16();


       //int[] nums = {-1,2,1,-4};
        //int[] nums = {1,1,-1,-1,3};

        //int[] nums = {1,1,1,0};
       // int[] nums = {0,0,0,0};
        //int[] nums = {0,0,0};
        int[] nums = {-1,0,1,1,55};
        System.out.println(leetCode16.threeSumClosest(nums,3));
    }

    public int threeSumClosest(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        int l;
        int r;

        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length -2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            l = i + 1;
            r = nums.length  - 1;
            while (l < r){
               int sum = nums[i] + nums[l] + nums[r];
               if (sum == target){
                   while (l < r && nums[r] == nums[r - 1]) r --;
                   while (l < r && nums[l] == nums[l + 1]) l ++;
                   return sum;
               }
               if (Math.abs(sum - target) < Math.abs(result - target)){
                   result = sum;
               }
               if (sum > target){
                   r --;
               }
               else {
                   l ++;
               }
            }

        }

       return result;
    }


}
