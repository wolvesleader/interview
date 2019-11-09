package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-11-09
 */
public class LeetCode9 {

    public static void main(String[] args) {
        LeetCode9 leetCode9 = new LeetCode9();
        System.out.println(leetCode9.isPalindrome(-121));
        System.out.println(leetCode9.isPalindrome(1001));
        System.out.println(leetCode9.isPalindrome(10));
    }

    public boolean isPalindrome(int x) {

        if (x <= 0) return false;

        //List<Integer> arrs = new ArrayList<>();
        int[] arrs = new int[10];
        int index = 0;

        while (x != 0){
            int G = x % 10;
            x = x / 10;
            //arrs.add(G);
            arrs[index++] = G;
        }


        for (int i = 0; i < index ; i++) {
            if ((i < index/2 ) && ( arrs[i] != arrs[index - 1 - i]) ) return false;
        }

        return  true;
    }
}
