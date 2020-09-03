package com.quincy.java.algorithm.leetcode;

/**
 *
 */
public class LeetCode7 {

    public static int reverse(int x) {
        int dest = 0;

        while (x != 0){
            int last = x % 10;
            x = x / 10;
            if (dest > Integer.MAX_VALUE / 10 || (dest == Integer.MAX_VALUE / 10 && dest > 7)) return 0;
            if (dest < Integer.MIN_VALUE / 10 || (dest == Integer.MIN_VALUE / 10 && dest < -8)) return 0;
            dest = dest * 10 + last;

        }

        return dest;
    }


    public static void main(String[] args) {
        int x = 123;

        System.out.println(reverse(x));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(2,31) - 1);

    }
}
