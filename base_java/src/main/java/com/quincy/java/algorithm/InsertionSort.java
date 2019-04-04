package com.quincy.java.algorithm;

import java.util.Arrays;

/**
 * Created by quincy on 18/4/17.
 *时间复杂度
 *外层循环  n - 1 次
 * 内层循环
 * 1 2 3 .... n
 * 根据等差数列求和公式
 * n*(n + 1) / 2
 *
 * 时间复杂度为 O(n*n)
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 8, 4, 2, 5};
        insertionSort(arr);
        Arrays.sort(arr);
    }

    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length ; i ++){
            for(int j = i - 1;j >= 0 && arr[j] > arr[j + 1];j --){
                swap(arr,j,j + 1);
            }
        }
        printArray(arr);
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
