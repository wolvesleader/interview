package com.quincy.java.algorithm.day03;

/**
 * Created by quincy on 18/5/15.
 * 转圈打印矩阵
 【题目】 给定一个整型矩阵matrix,请按照转圈的方式打印它。 例如:
       1  2 3  4
       5  6 7  8
       9 10 11 12
      13 14 15 16 打印结果为:1,2,3,4,8,12,16,15,14,13,9, 5,6,7,11, 10
 【要求】 额外空间复杂度为O(1)
 */
public class PrintMatrixSpiralOrder {


    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }

    private static void spiralOrderPrint(int[][] matrix) {

        int topRow = 0;
        int topColumn = 0;

        int downRow = matrix.length - 1;
        int downColumn = matrix[0].length - 1 ;

        //当上边界小于等于下边界我们可以不断的循环
        while(topRow <= downRow && topColumn <= downColumn){
            printEdge(matrix,topRow++,topColumn++,downRow--,downColumn--);
        }


    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC){

        //移动到同一行了,行不变，列不断递加
        if(tR == dR){
            for(int i = tC;i <= dC;i ++){
                System.out.print(m[tR][i]+ " ");
            }
        }else if(tC == dC){
            for (int i = tR;i <= dR;i ++){
                System.out.print(m[i][tC]+ " ");
            }
        }else{
            int currentColumn = tC;
            int cureentRow = tR;
            while(dC != currentColumn){
                System.out.print(m[tR][currentColumn]+ " ");
                currentColumn ++;
            }

            while (dR != cureentRow){
                System.out.print(m[cureentRow][dC]+ " ");
                cureentRow ++;
            }

            while (currentColumn != tC) {
                System.out.print(m[dR][currentColumn] + " ");
                currentColumn--;
            }
            while (cureentRow != tR) {
                System.out.print(m[cureentRow][tC] + " ");
                cureentRow--;
            }

        }
    }
}
