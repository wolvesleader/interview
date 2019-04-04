package com.quincy.java.base.ideadebug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quincy on 18/5/24.
 * 1.打断点
     1.1 怎么选择打断点的位置
   2.运行断点
     2.1 单步运行
     2.2 全部运行完
     2.3 进入方法中
     2.4 运行中查看变量的值  选中要查看看的变量右键，点击Evaluate Expression
     2.5 运行到光标所在行(运行到指定行) run to cursor
     2.6 step into和force step into的区别 会进入自己写的方法,而force step into能够进入所有的方法,比如jdk或者其他第三方提供的代码
   3.查看全部的断点
   4.禁止全部的断点
   5.条件断点

 */
public class IdeaDebug {
    public static void main(String[] args) {

        printNumber();

        ArrayList<String> lists = new ArrayList<>();
        lists.add("a");
        lists.add("b");
        lists.add("c");
        lists.add("d");

        String result = getResult(lists);
        System.out.println(result);
        exception();
    }


    public static void printNumber(){
        for (int i = 0 ; i < 100; i ++){
            System.out.print(i +" ");
        }
    }

    public static String getResult(List<String> lists){

        if (lists == null || lists.isEmpty()) return null;
        StringBuffer stringBuffer = new StringBuffer();
        for (String str: lists) {
            stringBuffer.append(str).append(" ");
        }
        return stringBuffer.toString();
    }


    public static void exception(){
        String str = null;
        System.out.println(str.toString());
    }
}
