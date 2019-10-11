package com.quincy.java.algorithm.findnumber;

/**
 * Created by quincy on 2018/12/16.
 */
public class FindNumberDriver {

    public static void main(String[] args) {

        int[] sourceData = {0,3,5,6,12,18,30};
        boolean result = findNumber1(sourceData,31,5);
        System.out.println(result);
    }

    /**
     *
     * @param sourceData 元数据
     * @param targetNumber 从元数据中查找的数据
     * @param bit  在元数据中最大数字转换为二进制的位数
     * @return
     */
    private static boolean findNumber1(int[] sourceData, int targetNumber, int bit) {

        boolean result = false;
        if ( (sourceData.length == 0 ) || targetNumber > (1<<bit) ){
           return result;
        }
        //我们这里采用数组存放，集合也可以
        int[] left = new int[sourceData.length];//bit位为1的放在左边
        int[] right = new int[sourceData.length]; //bit位为0的放在右边

        //
        for (int i = bit; i > 0 ; i--) {
            //循环遍历判断最高位1或0
            for (int j = 0,leftIndex=0,rightIndex=0; j < sourceData.length; j++) {
                //(sourceData(j) & (1 << i))获取最高位置,次高位.......直到最后一位
                if ( ( sourceData[j] & (1 << i) ) > 0 ){
                    left[leftIndex++] = sourceData[j];
                }else{
                    right[rightIndex++] = sourceData[j];
                }
            }
            if ((targetNumber & (1 << i)) > 0) {//从左边的集合中查找
                sourceData = left;
            } else {
                sourceData = right;
            }
        }

        result = left[0] == targetNumber || right[0] == targetNumber;

        return result;
    }

}
