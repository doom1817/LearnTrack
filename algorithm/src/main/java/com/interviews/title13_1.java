package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/06/08/09:20
 * @description:
 * 力扣74. 搜索二维矩阵
 *
 */
public class title13_1 {
    /**
     * 在一个每行从左到右递增、且每行第一个整数大于上一行最后一个整数的二维矩阵中搜索目标值。
     * 将二维矩阵视为一维有序数组，使用二分查找算法进行搜索。
     *
     * @param matrix 给定的二维整数矩阵
     * @param target 要搜索的目标整数
     * @return 如果目标值存在于矩阵中则返回 true，否则返回 false
     */
    private static boolean searchMatrix(int[][] matrix, int target){
        if (matrix == null|| matrix.length == 0|| matrix[0].length ==0)return false;
        int m = matrix.length;
        int n = matrix[0].length;
        // 初始化二分查找的左右边界，将整个矩阵映射为一维区间 [0, m*n-1]
        int left = 0,right = m * n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            // 将一维索引 mid 转换为二维矩阵的行号和列号
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target)return true;
            else if (matrix[row][col] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));// true
    }
}
