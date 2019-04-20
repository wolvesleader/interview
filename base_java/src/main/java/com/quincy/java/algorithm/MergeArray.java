package com.quincy.java.algorithm;

import java.util.Arrays;

/**
 * author:quincy
 * Date:2019-04-18
 */
public class MergeArray {
    public static void main(String[] args) {
        int a[] = {1,3,8,20};
        int b[] = {2,3,11,12,15,26};
        int c[] = new int[a.length + b.length];

        int aIndex = 0;
        int bIndex = 0;
        int i = 0;
        while(aIndex < a.length && bIndex < b.length){
            if(a[aIndex]<b[bIndex]){
                c[i++] = a[aIndex++];
            }else{
                c[i++] = b[bIndex++];
            }
        }
        while(aIndex < a.length){
            c[i++] = a[aIndex++];
        }

        while(bIndex < b.length){
            c[i++] = b[bIndex++];
        }


        System.out.println(Arrays.toString(c));
    }
}
