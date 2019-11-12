package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-11-09
 */
public class LeetCode10 {
    public static void main(String[] args) {
         LeetCode10 leetCode10 = new LeetCode10();
        /*System.out.println(leetCode10.isMatch("aa", "a"));
        System.out.println(leetCode10.isMatch("ab", ".*"));
        System.out.println(leetCode10.isMatch("aaa", "ab*a*c*a"));
        System.out.println(leetCode10.isMatch("aa", "a*"));
        System.out.println(leetCode10.isMatch("aab", "c*a*b"));*/
        //System.out.println(leetCode10.isMatch("mississippi", "mis*is*p*."));

      /*  String str = "asf";
        str = str.substring(1);
        System.out.println(str);*/
        System.out.println(leetCode10.isMatch("aa", "a*"));

    }

    public boolean isMatch(String s, String p) {
        int sLen = s.length(),pLen = p.length();

        if (pLen == 0) return sLen == 0;

        boolean first = ( sLen != 0 ) && ( (s.charAt(0) == p.charAt(0)) ||  p.charAt(0) == '.');

        //如果第二个元素是*
        if (pLen >= 2 && p.charAt(1) == '*'){
          //*代表前边的元素出现0次一次或者多次
                    //出现0次                     //出现一次或者多次
            return isMatch(s, p.substring(2)) || (first && isMatch(s.substring(1), p) );
        }
        else {
            boolean match = isMatch(s.substring(1), p.substring(1));
            return first && match ;
        }

    }
}
