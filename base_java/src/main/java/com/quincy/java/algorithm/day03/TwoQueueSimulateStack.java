package com.quincy.java.algorithm.day03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by quincy on 18/5/15.
 * 如何仅用队列结构实现栈结构?
 */
public class TwoQueueSimulateStack {
    private Queue<Integer> queue;
    private Queue<Integer> help;

    public TwoQueueSimulateStack(){
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public Integer pop(){
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (queue.size() != 1){
            help.add(queue.poll());
        }
        //重点，需要把地址的引用交换，以便于下次再次存储
        int stackTop = queue.poll();
        swap(queue,help);
        return stackTop;
    }

    public Integer peek(){
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (queue.size() != 1){
            help.add(queue.poll());
        }
        //重点，需要把地址的引用交换，以便于下次再次存储
        int stackTop = queue.poll();
        help.add(stackTop);
        swap(queue,help);
        return stackTop;
    }

    public void push(int obj){
        queue.add(obj);
    }

    /**
     * 交换引用
     * @param queue
     * @param help
     */
    private void swap(Queue<Integer> queue,Queue<Integer> help){

        Queue<Integer> temp = queue;
        queue = help;
        help = temp;

    }

 }
