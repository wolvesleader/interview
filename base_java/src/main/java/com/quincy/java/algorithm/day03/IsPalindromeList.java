package com.quincy.java.algorithm.day03;

import java.util.Stack;

/**
 * Created by quincy on 18/5/19.
 * 判断链表是否为回文
 */
public class IsPalindromeList {

    private class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static  boolean isPalindrome3(Node head){

        return false;
    }

    /**
     * 快慢指针的思想
     * @param head
     * @return
     */
    public static  boolean isPalindrome2(Node head){

        //通过快慢指针找到链表中间位置的节点
        Node fast = head.next;
        Node slow = head;

        while (slow.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> nodes = new Stack<>();


        return true;
    }

    public static boolean isPalindrome1(Node head){
        Node cur = head;
        Stack<Node> nodes = new Stack<>();
        while (cur.next != null){
            nodes.push(cur);
            cur = cur.next;
        }
        //遍历
        while (head != null){
            if(head.value != nodes.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {

    }



}

