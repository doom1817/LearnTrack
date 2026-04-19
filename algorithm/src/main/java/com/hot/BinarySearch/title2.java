package com.hot.BinarySearch;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/18/14:02
 * @description:
 *   力扣74. 搜索二维矩阵
 */
public class title2 {
    public static void main(String[] args) {
//        System.out.println(searchMatrix(
//                new int[][]{
//                        {1,3,5,7},
//                        {10,11,16,20},
//                        {23,30,34,60}
//                },3
//        )); //true
        System.out.println(searchMatrix(
                new int[][]{{1,3}},3
        )); // true
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        //边界判断
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int left =0,right = cols*rows-1;
        while (left<=right){
            int mid = (left+right)>>>1;
            int row = mid/cols; // 求出中间的行数
            int col = mid%cols; // 求出中间的列数
            if (matrix[row][col] == target){
                return true;
            }
            else if (matrix[row][col] < target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return false;
    }
}
