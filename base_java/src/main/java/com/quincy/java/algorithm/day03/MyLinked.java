package com.quincy.java.algorithm.day03;

import java.util.LinkedList;

/**
 * Created by quincy on 2018/8/11.
 * java 模拟单向链表
 */
public class MyLinked<E> {

    private Node head;
    private int length;


    private class Node<E>{
        private E data;
        private Node next;
        public Node(E data){
            this.data = data;
        }
    }

    public void iterator(){
        Node current = head;
        while(current !=null){
            System.out.print(current.data+" ");
            current = current.next;
        }
    }

    public void add(E t){
        Node newNode = new Node(t);
        if(head == null){
            head = newNode;
            return;
        }
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
        length ++;
    }

    public static void main(String[] args) {
        MyLinked<String> myLinked = new MyLinked<>();
        myLinked.add("111");
        myLinked.add("222");
        myLinked.iterator();
    }
}
