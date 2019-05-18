package com.quincy.java.algorithm;

/**
 * Created by quincy on 18/4/15.
 * 时间复杂度
 * 外层 n
 * 内层 n n-1 n-2 n-3 .... 1
 * 利用等差数列求和公司 n*((n-1) + 1)/2
 * 时间复杂度为O(n*n)
 * O(n*n)
 * 7, 1, 3, 8, 4, 2, 5
 *                   7
 */
public class BubbleSort {

    public static void main(String[] args) {
        //System.out.println((byte) 128);
        int[] arr = {7, 1, 3, 8, 4, 2, 5};
        bubbleSort(arr);

    }


    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
        //printArray(arr);
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void printArray(int[] arr){
        for(int i = 0 ; i < arr.length;i ++){
            System.out.print(arr[i] + " ");
        }
    }


}
