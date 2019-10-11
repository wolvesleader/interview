package com.quincy.java.algorithm.day03;

/**
 * Created by quincy on 18/5/17.
 */
public class RotateMatrix {


    public static void rotate(int[][] matrix) {

        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        while (tR < dR){
            rotateEdge(matrix,tR++,tC++,dR--,dC--);
        }

    }
    public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {

        //求出一行需要交换的次数
        int times = dC - tC;
        int temp = 0;

        for (int i = 0; i < times ; i ++){
            temp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = temp;
        }

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
