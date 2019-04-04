package com.quincy.java.algorithm;

/**
 * Created by quincy on 18/4/15.
 * 时间复杂度
 * 外层循环n次
 * 内层循环 n-1 n-2 n-3 ... 1
 * 等差数列求和
 * n*((n-1) + 1)/2
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 8, 4, 2, 5};
        selectionSort(arr);
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
               // if (arr[i] > arr[j]) {
                    //swap(arr, i, j);
                    minIndex = arr[j] < arr[minIndex] ? j : minIndex;
                //}
            }
            swap(arr,i,minIndex);
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
