package com.hot.matrix;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/03/02/22:46
 * @description:
 *  力扣 48.旋转图像
 */
public class title3 {
    public static void main(String[] args) {
        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        rotate(new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
    }

    /**
     * 将 n × n 矩阵顺时针旋转 90 度
     * 使用分层旋转的方法，从外层到内层依次旋转
     *
     * @param matrix 需要旋转的二维整数矩阵
     */
    public static void rotate(int[][] matrix) {
        //上下翻转矩阵
        for (int i = 0; i < matrix.length / 2; i++){
            int[] temp = matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = temp;
        }
        //沿主对角线转置矩阵
        for (int i = 0; i < matrix.length; i++){
            for (int j = i + 1; j < matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }
}
