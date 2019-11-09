package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-11-06
 */
public class LeetCode8 {
    public  int myAtoi(String str) {

        if ("".equals(str) || str.length() == 0) return 0;
        String unTrimStr = str.trim();

        StringBuffer sb = new StringBuffer("");

        //0-1
        //+-
        //--

        for (int i = 0; i < unTrimStr.length(); i++) {
            char currentChar = unTrimStr.charAt(i);
            if ( (currentChar >= '0' && currentChar <= '9')
                    || ( currentChar == '-' && i == 0 )
                    || ( currentChar == '+' && i == 0 ) ){
                sb.append(currentChar);
            }else{
                break;
            }
        }
        String numStr = sb.toString();

        return strNumToNum(numStr);
    }

    /**
     * 把数字类型的字符串转换为数字
     * @param numStr
     * @return
     */
    private  int strNumToNum(String numStr){

        if ("".equals(numStr) || numStr.length() == 0) return 0;
        int result = 0,i = 0,len = numStr.length();
        boolean negative = false;
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        char firstChar = numStr.charAt(0);
        if (firstChar < '0'){
            if (firstChar == '-'){
                negative = true;
                limit = Integer.MIN_VALUE;
            }
            if (len == 1) return result;
            i ++;
        }

        multmin = limit / 10;
        while (i < len) {
            digit = Character.digit(numStr.charAt(i++),10);
            if ( (result < multmin) || (result == multmin && digit > 7)) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result *= 10;
            result -= digit;

        }

        return negative ? result : -result;
    }

    public static void main(String[] args) {

        LeetCode8 leetCode8 = new LeetCode8();
        //System.out.println(Integer.MIN_VALUE);//-2147483648

        //Integer.parseInt("2147483648");

        //System.out.println(Integer.MAX_VALUE);//2147483647
        System.out.println(leetCode8.myAtoi("0-1"));
        System.out.println(leetCode8.myAtoi("0++"));
        System.out.println(leetCode8.myAtoi("01"));
        System.out.println(leetCode8.myAtoi("-+"));
        System.out.println(leetCode8.myAtoi("+-"));
        System.out.println(leetCode8.myAtoi("-91283472332"));
        System.out.println(leetCode8.myAtoi("42"));
        System.out.println(leetCode8.myAtoi("   -42"));
        System.out.println(leetCode8.myAtoi("4193 with words"));
        System.out.println(leetCode8.myAtoi("words and 987"));



    }
}
