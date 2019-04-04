package com.quincy.java.algorithm.orphanage;


/**
 * Created by quincy on 18/4/25.
 */
public class Palindrome {
    /**
     * 判断是否为回文
     * @param pHead
     * @return
     */
    public static boolean isPalindrome(ListNode pHead) {

        StringBuffer sb = new StringBuffer();
        if(pHead == null){
            return false;
        }

        ListNode node = pHead;

        while(node != null){
            sb.append(node.val);
            node = node.next;
        }
        return sb.toString().equals(sb.reverse().toString());
    }


    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);

        ListNode listNode1 = new ListNode(1);

        listNode.next = listNode1;

        boolean palindrome = isPalindrome(listNode);
        System.out.println(palindrome);

    }
}
