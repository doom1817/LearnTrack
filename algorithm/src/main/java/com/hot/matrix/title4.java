package com.hot.matrix;

/**
 * @author: doom
 * @date: 2026/03/02/22:58
 * @description:
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null|| matrix.length == 0|| matrix[0].length ==0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0, col = n-1; //初始位置 在矩阵的右上角
        while (row < m && col >= 0){
            if (matrix[row][col] == target){
                return true;
            }else if (matrix[row][col] > target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}
