package com.hot.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/03/02/22:33
 * @description:
 *  力扣 54. 螺旋矩阵
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    /**
     * 按照螺旋顺序返回矩阵中的所有元素
     * 使用四个边界指针（left、right、top、bottom）来控制遍历方向
     *
     * @param matrix 输入的二维整数矩阵
     * @return 按螺旋顺序排列的元素列表
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length; //行数
        int n = matrix[0].length; //列数

        // 初始化四个边界指针
        int left = 0, right = n - 1, top = 0, bottom = m - 1;

        // 循环条件：当左右边界和上下边界未交叉时继续遍历
        while (left <= right && top <= bottom) {
            //从左往右遍历顶部行
            for (int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            top++;

            //从上往下遍历右侧列
            for (int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;

            //从右往左遍历底部行（需要检查上下边界是否交叉）
            if (top <= bottom){
                for (int i = right; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            //从下往上遍历左侧列（需要检查左右边界是否交叉）
            if (left <= right){
                for (int i = bottom; i >= top; i--){
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}
