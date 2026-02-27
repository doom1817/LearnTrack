package com.DP.day5;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/01/22:03
 * @Description:
 * 力扣72 编辑距离
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }
    /**
     * 计算将 word1 转换为 word2 所需的最少编辑操作次数。
     * 允许的操作包括：插入一个字符、删除一个字符、替换一个字符。
     * 使用动态规划求解，时间复杂度 O(m*n)，空间复杂度 O(m*n)。
     *
     * @param word1 源字符串
     * @param word2 目标字符串
     * @return 最小编辑距离
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最小操作数
        int[][] dp = new int[m + 1][n + 1];

        //初始化边界条件

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 当 word1 为空（i=0），需插入 j 个字符以得到 word2 的前 j 个字符 → 操作数为 j
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 当 word2 为空（j=0），需删除 word1 的全部 i 个字符 → 操作数为 i
        }
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++) {
                //如果当前字符相等，无需操作，直接继承上一行相同列的值
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                }
                //状态转移方程 dp[i][j] = Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j],dp[i][j-1]))+1;
                //其中dp[i-1][j-1]+1表示替换；dp[i-1][j]表示删除；dp[i][j-1]+1表示插入
            }
        }
        //返回最小编辑距离
        return dp[m][n];
    }
}
