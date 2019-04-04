package com.quincy.java.algorithm.day03;

/**
 * Created by quincy on 18/5/14.
 * 数组结构模拟栈
 * 栈结构的特点 FILO
 */

public class ArrayStack {

    private  Integer[] arr;
    private int size ;

    public ArrayStack(int size){
        if(size < 0) throw new NegativeArraySizeException("array size must more than 0");
        //创建固定长度的数组
        arr = new Integer[size];
        size = 0;
    }

    /**
     * 弹出栈顶元素，但是不会移除栈顶元素
     */
    public Integer peek(){
        if (size == 0) return null;
        //减一的原因是数组索引是从0开始的
        return arr[size - 1];
    }

    public Integer pop(){
        if (size == 0) return null;
        return arr[--size];
    }

    public void push(int integer){
        if (size == arr.length) throw  new ArrayIndexOutOfBoundsException("the stack is full");
        arr[size ++] = integer;
    }

}
