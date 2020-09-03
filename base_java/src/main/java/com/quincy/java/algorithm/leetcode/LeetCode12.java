package com.quincy.java.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class LeetCode12 {
    public static void main(String[] args) {
        LeetCode12 leetCode12 = new LeetCode12();
        int  temp = 2;

        System.out.println(leetCode12.intToRoman(temp));


    }

    public String intToRoman(int num) {

        int bl = 1;

                         //1   2    3     4    5   6    7    8       9   10
        String[] chars = {"I","II","III","IV","V","VI","VII","VIII","IX","X",
                          //20   30  40   50   60  70     80    90  100  200
                          "XX","XXX","XL","L","LX","LXX","LXXX","XC","C","CC",
                          //300   400  500  600  700   800   900  1000
                          "CCC","CD","D","DC","DCC","DCCC","CM","M"};
        int[] ints = {1,2,3,4,5,6,7,8,9,10,
                           20,30,40,50,60,70,80,90,100,200,
                           300,400,500,600,700,800,900,1000};
        HashMap<Integer,String> hashMap = new HashMap<Integer,String>(){
            {
                put(1,"I");put(2,"II");put(3,"III");put(4,"IV");put(5,"V");put(6,"VI");put(7,"VII");put(8,"VIII");put(9,"IX");
                put(10,"X");put(20,"XX");put(30,"XXX");put(40,"XL");put(50,"L");put(60,"LX");put(70,"LXX");put(80,"LXXX");put(90,"XC");
                put(100,"C");put(200,"CC");put(300,"CCC");put(400,"CD");put(500,"D");put(600,"DC");put(700,"DCC");put(800,"DCCC");put(900,"CM");
                put(1000,"M");put(1000,"MM");put(1000,"MMM");put(0,"");

            }
        };

        List<String> list = new ArrayList<>();
        while (num != 0){
            int G = num % 10;
            num /= 10;
            bl *= 10;
            int current = G*(bl/10);
            list.add(0,hashMap.get(current));
        }

        return list.toString().replace(", ","").replace("[","").replace("]","");
    }
}
