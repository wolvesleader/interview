package com.quincy.java.algorithm.day03;

import java.lang.reflect.Proxy;
import java.util.Stack;

/**
 * Created by quincy on 18/5/14.
 * 实现一个特殊的栈,在实现栈的基本功能的基础上,再实现返
 回栈中最小元素的操作
 */
public class MyStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MyStack(){
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public Integer pop(){
        if (this.minStack.isEmpty()) throw new RuntimeException("data stack is empty");
        this.minStack.pop();
        return this.dataStack.pop();
    }

    public void push(int newNum){
        if (this.minStack.isEmpty()){
            this.minStack.push(newNum);
        }else if(newNum > getMin()){
            this.minStack.push(getMin());
        }else{
            this.minStack.push(newNum);
        }
        this.dataStack.push(newNum);
    }

    public  Integer getMin(){
        if (this.minStack.isEmpty()) throw new RuntimeException("min stack is empty");
        return this.minStack.peek();
    }


}
