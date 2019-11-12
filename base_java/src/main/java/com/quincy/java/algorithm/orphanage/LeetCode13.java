package com.quincy.java.algorithm.orphanage;

import java.util.HashMap;

/**
 * author:quincy
 * Date:2019-11-11
 *  I             1
*   V             5
*   X             10
*   L             50
*   C             100
*   D             500
*   M             1000
 */
public class LeetCode13 {
    public static void main(String[] args) {
        LeetCode13 leetCode13 = new LeetCode13();
        int  temp = 2;
        String str = "MCMXCIV";
        System.out.println(leetCode13.romanToInt(str));

    }

    public int romanToInt(String s) {

        int num = 0;
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>(){
            {
                put("I",1);put("IV",4);put("V",5);put("IX",9);
                put("X",10);put("XL",40);put("L",50);put("XC",90);
                put("C",100);put("CD",400);put("D",500);put("CM",900);
                put("M",1000);put("",0);

            }
        };

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I' && (i + 1) < s.length() && s.charAt(i + 1) == 'V'){
                num += hashMap.get("IV");
                i ++;
            }
            else if (s.charAt(i) == 'I' && (i + 1) < s.length() && s.charAt(i + 1) == 'X'){
                num += hashMap.get("IX");
                i ++;
            }

            else if (s.charAt(i) == 'X' && (i + 1) < s.length() && s.charAt(i + 1) == 'L'){
                num += hashMap.get("XL");
                i ++;
            }

            else if (s.charAt(i) == 'X' && (i + 1) < s.length() && s.charAt(i + 1) == 'C'){
                num += hashMap.get("XC");
                i ++;
            }

            else if (s.charAt(i) == 'C' && (i + 1) < s.length() && s.charAt(i + 1) == 'D'){
                num += hashMap.get("CD");
                i ++;
            }

            else if (s.charAt(i) == 'C' && (i + 1) < s.length() && s.charAt(i + 1) == 'M'){
                num += hashMap.get("CM");
                i ++;
            }
            else{
                num += hashMap.get(s.charAt(i)+"");
            }



        }


        return num;
    }



}
