package com.quincy.java.algorithm.orphanage;

import java.util.Stack;

/**
 * author:quincy
 * Date:2019-12-04
 * 分解数字
 */
public class ResolvedNumber {
    Stack stack = new Stack();
    Stack stackB = new Stack();


    public static void main(String[] args) {

        int a = 5;
        ResolvedNumber d = new ResolvedNumber();
        d.dfs(a,4);
    }


    private void dfs(int n,int m){

        if (m == 0){//右子树完毕
            return;
        }
        if (n == 0){//左子树完毕
            stackB.addAll(stack);
            int temp = stackB.size();
            int i = 0;
            while (i < temp){
                if (i == temp - 1){
                    System.out.print(stackB.peek() + " ");
                    stackB.pop();
                    break;
                }
                System.out.print(stackB.peek() + " +" + " ");
                stackB.pop();
                i ++;
            }
            System.out.println();
            return;
        }

        if (n < m){
            m = n;
        }
        stack.push(m);
        dfs(n - m,m);
        stack.pop();
        dfs(n,m - 1);
    }
}
