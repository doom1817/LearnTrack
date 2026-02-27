package com.DP.day3;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/28/23:30
 * @Description:
 * 力扣221. 最大正方形
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
    }
    public static int maximalSquare(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int rows =matrix.length;
        int cols =matrix[0].length;
        int[][] dp=new int[rows][cols];
        int maxSide = 0;
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (matrix[i][j] == '1') {
                    if (i==0||j==0){
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                    }
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }
        }

        return maxSide*maxSide;
    }
}
