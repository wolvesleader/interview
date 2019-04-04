package com.quincy.java.algorithm;

/**
 * Created by quincy on 18/5/13.
 */
public class HeapSort {
    public static void main(String[] args) {
        int [] arr = {5,6,7,0,1,9,3,5,4};
        heapSort(arr);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static  void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        //构建跟堆
        for(int i = 0; i < arr.length ; i ++){
            heapInsert(arr,i);
        }

        int size = arr.length;//获取数组的长度，也就是我们控制的边界判断条件变量
        //大跟堆0位置的元素是数组中值最大的元素，我们把0位置的元素和最大位置的元素交换
        swap(arr,0,--size);

        while(size > 0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
        printArray(arr);
    }

    /**
     * 调整跟堆
     */
    public static void heapify(int[] arr , int index,int size){
        //当前索引的左节点
        int left = 2 * index + 1;
        while (left < size){//没有越界
            int right = left + 1;//右节点
            //求最大左节点和右节点的最大值
            int largest = right < size && arr[left] < arr[right] ? right : left;
            //判断左右节点的最大值和当前索引的最大值
            largest = arr[index] < arr[largest] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    /**
     * 构建大根堆或者小跟堆
     */
    public static void heapInsert(int[] arr,int index){

        //当前的数比父节点的数小,需要进行交换
        if(arr[index] > arr[(index - 1) / 2]){
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2; //继续向上调整
        }
    }


    public static void swap(int[] arr , int i , int j) {
        int temp = arr[i];
        arr[i ] = arr[j];
        arr[j] = temp;
    }

}
