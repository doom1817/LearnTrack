package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/12/10:03
 * @description:
 */
public class title3_3 {
    public static void main(String[] args) {
        rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
    private static void rotate(int[][] matrix){
        if (matrix == null || matrix.length == 0|| matrix[0].length == 0){return;}
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++){
            int end = n - 1 - layer;
            for (int i = layer; i < end- layer; i++){
                int temp = matrix[layer][layer+i];

                // 左边 -> 上边
                matrix[layer][layer +i] = matrix[end-i][layer];

                // 下边 -> 左边
                matrix[end-i][layer] = matrix[end][end-i];

                // 右边 -> 下边
                matrix[end][end-i] = matrix[layer +i][end];

                // 上边（临时保存）-> 右边
                matrix[layer +i][end] = temp;
            }
        }
        System.out.println(Arrays.deepToString( matrix));
    }
}
