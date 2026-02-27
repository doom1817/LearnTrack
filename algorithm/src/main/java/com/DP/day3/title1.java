package com.DP.day3;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/28/23:19
 * @Description:
 * 力扣 921.下降路径最小和
 */
public class title1 {
    public static void main(String[] args) {
//        matrix = [[2,1,3],[6,5,4],[7,8,9]]
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum(matrix));
    }
    public static int minFallingPathSum(int[][] matrix) {
        int n=matrix.length; //n x  n的正方形
        int[][] dp=new int[n][n];

        //初始化第一行
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i=1;i<n;i++){
            for (int j = 0; j < n; j++) {
                int minPrev = dp[i-1][j];
                if (j>0){
                    minPrev=Math.min(minPrev,dp[i-1][j-1]); //左上角
                }
                if (j<n-1){
                    minPrev=Math.min(minPrev,dp[i-1][j+1]); //右上角
                }
                dp[i][j]=matrix[i][j]+minPrev;
            }
        }
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            res=Math.min(res,dp[n-1][i]);
        }
        return res;
    }
}
