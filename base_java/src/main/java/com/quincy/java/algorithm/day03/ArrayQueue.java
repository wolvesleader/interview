package com.quincy.java.algorithm.day03;

/**
 * Created by quincy on 18/5/14.
 * 数组模拟队列结构
 * FIFO
 */
public class ArrayQueue {

    private  int size;
    private  Integer[] arr;
    private int start;
    private int end;

    public ArrayQueue(int initSize){
        if (initSize < 0) throw new NegativeArraySizeException("array size must more than 0");
        arr = new Integer[initSize];
        size = 0;
        start = 0;
        end = 0;
    }

    public Integer peek(){
        if (size == 0) return null;
        return arr[start];
    }

    public Integer poll(){
        if (size == 0)throw new ArrayIndexOutOfBoundsException("The queue is empty");
        size --;
        int temp = start;
        start = start == arr.length - 1 ? 0 : start + 1;
        return arr[temp];
    }

    public void push(int obj){
        if (size == arr.length) throw new ArrayIndexOutOfBoundsException("The queue is full");
        size ++;
        arr[end ++] = obj;
        end = end == arr.length -1 ? 0 : end + 1;
    }

}
