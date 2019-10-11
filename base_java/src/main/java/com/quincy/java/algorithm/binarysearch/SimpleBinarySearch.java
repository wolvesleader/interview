package com.quincy.java.algorithm.binarysearch;

/**
 * author:quincy
 * Date:2019-06-08
 * 查找思想
 * 目标值和计算中间的值比较
 */
public class SimpleBinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1,5,7,12,34,100};
        //查找一个数字是否在这个数组中
        System.out.println(findNumber(arr, 10));
    }

    //返回的是目标值在数组中的索引位置
    private static int findNumber(int[] arr,int target) {
        int targetIndex = -1;
        if (arr == null || arr.length == 0){
            return targetIndex;
        }
        int length = arr.length - 1 ;
        int start = 0;
        int end = length;
        while (start <= end){
            //此种写法存在问题，数据较大2这数据相加会出现溢出
            //int middle = (start + end) / 2;
            //int middle = start + (end - start) / 2;
            //优化为位运算比较快
            int middle = start + ( (end - start) >> 1 );
            //目标值刚好数据中间的数据
            if (target == arr[middle]){
                targetIndex = middle;
                 return targetIndex;
            }else if(target > arr[middle]){//目标值大于中间值，目标值应该在数组的后半段
                  //修改start再次开始查找
                start = middle + 1;
            }else{
                //修改结束的位置
                end = middle - 1 ;
            }
        }
        return targetIndex;
    }
}
