package com.hot.dp;

/**
 * @author: doom
 * @date: 2026/04/28/11:19
 * @description:
 *   力扣279. 完全平方数
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
    public static int numSquares(int n) {
        int[] dp = new int[n + 1]; // dp[i] 表示 i 的最小平方数
        //初始化
        for (int i = 1; i <= n; i++){
            dp[i] = i;
        }
        //状态转移方程
        for (int i = 2; i*i <= n; i++){
            int j= i* i; // i的平方
            for (int k=j; k<=n;k++){
                // 更新所有大于等于当前完全平方数的位置的dp值
                dp[k] = Math.min(dp[k], dp[k-j]+1);
            }
        }
        return dp[n];
    }
}
