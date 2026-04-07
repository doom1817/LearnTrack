package com.hot.matrix;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/03/02/22:15
 * @description:
 *  力扣73.矩阵置零
 */
public class title1 {
    public static void main(String[] args) {
        setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
        setZeroes(new int[][]{{1,1,1},{1,0,1},{1,1,1}});
    }
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length; //行数
        int n = matrix[0].length; //列数
        boolean firstRowHasZero = false; //第一行是否有0
        for (int i = 0; i < n; i++){
            if (matrix[0][i] == 0){
                firstRowHasZero = true;
                break;
            }
        }
        //如果某个元素为 0，则将其对应的第一列和第一行的元素标记为 0
        for (int i = 1; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0; //在第一列标记该行
                    matrix[0][j] = 0; //在第一行标记该列
                }
            }
        }
        //从右到左遍历，包括第一列 (j=0)
        for (int i = 1; i < m; i++){
            for (int j = n-1; j >= 0 ; j--){
                if (matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //如果第一行有0，则将第一行全部置0
        if (firstRowHasZero){
            Arrays.fill(matrix[0], 0);
        }

        System.out.println(Arrays.deepToString(matrix));
    }
}
