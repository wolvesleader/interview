package com.quincy.java.algorithm.orphanage;

/**
 * author:quincy
 * Date:2019-10-28
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 时间复杂度是o(n*n)
 * 测试通过
 */
public class LeetCode6 {

    public static String convert(String s, int numRows) {

        if (s == null) return null;
        if (s.length() == 1) return s;

        if(numRows == 1 )return s;

        int[][] arr = new int[numRows][s.length()];
        int row = 0,col = 0;
        boolean flag = false;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if ( count % numRows == 0 ){
                flag = !flag;
                i --;
            }else{
                if (flag){
                    arr[row++][col] = s.charAt(i);
                }else{
                    arr[row--][col++] = s.charAt(i);
                }
            }
            count ++;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0){
                    stringBuffer.append((char) arr[i][j]);
                }
            }
        }
       return stringBuffer.toString();
    }


    public static void main(String[] args) {

       /* int row = 4;
        String str = "LEETCODEISHIRING";
        System.out.println(str.length() / 2);*/

        //LCIRETOESIIGEDHN 3
        //LDREOEIIECIHNTSG 4
        //LIEESGEDHNTOIICR 5


        String leetcodeishiring = convert("ABC", 2);
        System.out.println(leetcodeishiring);

    }
}
