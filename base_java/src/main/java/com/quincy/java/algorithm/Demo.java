package com.quincy.java.algorithm;

import com.quincy.java.algorithm.pojo.ListNode;

import java.util.Scanner;

/**
 * Created by quincy on 18/4/15.
 */
public class Demo {
    public static void main(String[] args) {

        int endZeroCount = factorialEndZeroCount(10);
        System.out.println(endZeroCount);




    }


    /**
     * 一个数的阶乘末尾的0个数
     */
    public static int factorialEndZeroCount(int n){
        int count = 0;
        while (n > 0){
            n = n / 5;
            count = count + n;
        }
        return count;
    }

    public static Long factorial(int n){

        if(n < 1 || n > 1000){
            return 0L;
        }else if (n == 1 || n == 0){
            return 1L;
        }else {
            return n*factorial(n-1);
        }
    }



    /**
     * 进制转换器
     * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
     */
    public static void change(int M, int N) {
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuffer sb = new StringBuffer();
        boolean isNegative = M < 0 ;
        while ((M = Math.abs(M)) > 0) {
            int divided = M % N;
            sb.append(chars[divided]);
            M = M / N;
        }
        System.out.println((isNegative? "-": "")+sb.reverse());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        return null;
    }


    public static int f(int n, int count) {
        if (n <= 88480000) {
            System.out.println("结束");
            count++;
            return f(2 * n, count);
        } else {
            return count;
        }
    }


    public static void isPrime(int number) {

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                System.out.println("不是素数");
                break;
            }
        }

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int indexNum = nums[i];
            for (int j = 1; j < nums.length; j++) {
                if (indexNum + nums[j] == target && i != j) {
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
}
