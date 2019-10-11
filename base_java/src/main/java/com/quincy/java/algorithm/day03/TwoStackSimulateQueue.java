package com.quincy.java.algorithm.day03;

import java.util.Stack;

/**
 * Created by quincy on 18/5/15.
 * 如何仅用栈结构实现队列结构?
 * 2个栈模拟队列
 */
public class TwoStackSimulateQueue {


    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;


    public TwoStackSimulateQueue(){
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    /**
     * 添加数据
     * @param newValue
     */
    public void push(int newValue){
        pushStack.push(newValue);
    }

    public Integer poll(){
        //pushStack.empty();
        if(pushStack.isEmpty() && popStack.isEmpty()) throw new RuntimeException("Queue is empty!");
        if(popStack.isEmpty()){
            while (!pushStack.isEmpty()){//放入数据的栈不为空
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public Integer peek(){
        if(pushStack.isEmpty() && popStack.isEmpty()) throw new RuntimeException("Queue is empty!");
        if(popStack.isEmpty()){
            while (!pushStack.isEmpty()){//放入数据的栈不为空
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }


}

