package com.quincy.java.algorithm.orphanage;

import java.util.LinkedList;
import java.util.List;

/**
 * author:quincy
 * Date:2019-11-13
 * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
 * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
 * 　　peek       返回队列头部的元素             如果队列为空，则返回null
 * 　　put         添加一个元素                      如果队列满，则阻塞
 * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
public class LeetCode17 {

    public static void main(String[] args) {

        int numericValue = Character.getNumericValue('2');
        //System.out.println(numericValue);
        LeetCode17 leetCode17 = new LeetCode17();

        System.out.println(leetCode17.letterCombinations("234"));


    }

    public List<String> letterCombinations(String digits) {

        LinkedList<String> result = new LinkedList<>();

        if (digits == null || digits.length() == 0) return result;


        String[] source = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            //当前获取到的字符代表的数字
            int charPoint = Character.getNumericValue(digits.charAt(i));
            //
          while (result.peek().length() == i){
              String poll = result.poll();//移出并返回队列头部的元素
              char[] chars = source[charPoint].toCharArray();
              for (int j = 0; j < chars.length; j++) {
                   result.add(poll+chars[j]);
              }
          }
        }

        return result;
    }
}
