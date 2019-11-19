package com.quincy.java.algorithm.orphanage;

import java.util.Stack;

/**
 * author:quincy
 * Date:2019-11-17
 */
public class LeetCode20 {


    public static void main(String[] args) {

        LeetCode20 leetCode20 = new LeetCode20();
        System.out.println(leetCode20.isValid("{[]}"));
        System.out.println(leetCode20.isValid("]"));
        System.out.println(leetCode20.isValid("){"));
        //System.out.println(leetCode20.isValid(""));
        //System.out.println(leetCode20.isValid(""));
        //System.out.println(leetCode20.isValid(""));
        System.out.println(leetCode20.isValid(""));
        //System.out.println(leetCode20.isValid("(])"));


    }

    public boolean isValid(String s) {

        //if (s == null || s.length() < 2) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
                stack.push(s.charAt(i));
            }
            else if (stack.empty()){
                return false;
            }
            else {
                if (s.charAt(i) == ')') {
                    if (stack.peek() == '(' ){
                        stack.pop();
                    }
                }
                else if (s.charAt(i) == '}'){
                    if (stack.peek() == '{'){
                        stack.pop();
                    }
                }
                else if (s.charAt(i) == ']'){
                    if (stack.peek() == '['){
                        stack.pop();
                    }
                }
                else {
                    stack.push(s.charAt(i));
                }
            }
        }
        return stack.empty();
    }
}
