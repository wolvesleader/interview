package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-10-28
 */
public class LeetCode5 {

//"aaabaaaa"
    //"babadada"
    public static void main(String[] args) {
        String s = "aaabaaaa";
        String subStr = "";
        int subStrLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //第一次出现位置
            int start = s.indexOf(c);
            //最后一次出现位置
            int end = s.lastIndexOf(c);
            String substring = s.substring(start, end + 1);

            StringBuffer sb = new StringBuffer(substring);
            if (substring.equals(sb.reverse().toString()) && substring.length() > subStrLength){
                subStr = substring;
                subStrLength = substring.length();
            }
        }

        System.out.println(subStr);


    }
}
