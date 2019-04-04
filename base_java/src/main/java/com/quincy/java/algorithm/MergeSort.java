package com.quincy.java.algorithm;

import java.util.Arrays;

/**
 * Created by quincy on 18/4/19.
 * 归并排序，空间复杂度O(n)
 * T(n)<2T(n2)+cn  T(n)<4T(n4)+2cn  T(n)<8T(n8)+3cn
 * 可以看出规律了，而且很容易用归纳法证明。于是代入k次时就有（n=2k）
 * k:T(n)<2k T(n2k)+kcn=nT(1)+cnlog2n=Θ(nlogn)
 */
public class MergeSort {


    public static void main(String[] args) {

        int[] arr = {7, 1, 3, 8, 4, 2, 5};
       // mergeSort(arr,0,arr.length - 1 );
    }

    public static void mergeSort(int[] arr,int L,int R){
        if (arr == null || arr.length < 2){
            return ;
        }
        //base case
        if (L == R){//分解到最小了不能在分解了
            return ;
        }
        //求出中间值，然后分为左边和右边，分别把左边和右边排好序，在拷贝到我们定义的数组中
        int min = ( L + R ) / 2;
        //排序左边部分
        mergeSort(arr,L,min);
        //排序右半部分
        mergeSort(arr,min + 1,R);
        //开始排序
        merge(arr,L,min,R);


    }

    public static void merge(int[] arr,int L,int min,int R){
        //定义一个数组
        int[] help = new int[R - L + 1];
        System.out.println(help.length);
        int index = 0;// help数组的索引
        int p1 = L;//左边数组移动到的为止
        int p2 = min + 1; //右边数组移动到的位置
        //如果左边和右边的长度相同，都没有越界
        while (p1 <= min && p2 <= R){
            help[index++] = arr[p1] < arr[p2] ? arr[p1] : arr[p2];
        }
        //再考虑左边的数组越界，右边的没有越界，开始拷贝右边的数组
        while (p2<=R){
            help[index++] = arr[p2++];
        }

        while (p1<=L){
            help[index++] = arr[p1++];
        }

    }
}

