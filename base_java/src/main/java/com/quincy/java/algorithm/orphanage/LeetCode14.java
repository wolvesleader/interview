package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-11-12
 */
public class LeetCode14 {

    public static void main(String[] args) {
        LeetCode14 leetCode14 = new LeetCode14();
        //String[] strings = new String[]{"flower","flow","flight"};
        //String[] strings = new String[]{"ca","a"};
        //String[] strings = new String[]{"a"};
        // String[] strings = new String[]{"a","a"};
        String[] strings = new String[]{"a","ac"};
        System.out.println(leetCode14.longestCommonPrefix(strings));
    }

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String mixStr = strs[0];
        int m = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if ((strs[i].length() - m) <= 0){
                m = strs[i].length();
                mixStr = strs[i];
            }
        }
        String result = mixStr;
        for (int i = 0;i < mixStr.length(); i ++){
            for (int j = 0; j <strs.length ; j++) {
                //strs[0].charAt(i)第一个字符串，第一个字符
                if (mixStr.charAt(i) != strs[j].charAt(i)){
                    result = mixStr.substring(0,i);
                    return result;
                }
            }
        }
        return result;
    }
}
