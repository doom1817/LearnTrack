package com.DP.day5;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/01/22:20
 * @Description:
 * 力扣 712 . 两个字符串的最小ASCII删除和
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(minimumDeleteSum("sea", "eat"));
        System.out.println(minimumDeleteSumByLCS("delete", "leet"));
    }
    /**
     * 计算使两个字符串相等所需的最小ASCII删除和。
     * 使用动态规划求解，时间复杂度 O(m*n)，空间复杂度 O(m*n)。
     * 核心思想：保留最长公共子序列，删除其余字符，使得删除的ASCII值总和最小。
     *
     * @param s1 源字符串
     * @param s2 目标字符串
     * @return 最小ASCII删除和
     */
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // dp[i][j] 表示 s1[0...i-1] 和 s2[0...j-1] 的最小删除和
        int[][] dp = new int[m + 1][n + 1];


        //初始化第一行和第一列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            //当 s2 为空字符串时，需要删除 s1 的前 i 个字符，删除和为这些字符的ASCII值之和
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
            //当 s1 为空字符串时，需要删除 s2 的前 j 个字符，删除和为这些字符的ASCII值之和
        }
        // 状态转移方程
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 如果当前字符相等，无需操作，直接继承上一行相同列的值
                } else {
                    // 如果当前字符不相等，则需要删除一个字符，并计算删除和 找到最小的删除和
                    dp[i][j] = Math.min(
                            dp[i - 1][j] + s1.charAt(i-1), // 删除 s1 的当前字符
                            dp[i][j - 1] + s2.charAt(j-1) // 删除 s2 的当前字符
                    );
                }
            }
        }
        //直接返回最小删除和
        return dp[m][n];
    }
    /**
     * 通过最长公共子序列计算使两个字符串相等所需的最小ASCII删除和。
     * 使用动态规划求解，时间复杂度 O(m*n)，空间复杂度 O(m*n)。
     * 核心思想：先找到最长公共子序列的ASCII和，然后用两字符串总和减去两倍公共部分的和。
     *
     * @param s1 源字符串
     * @param s2 目标字符串
     * @return 最小ASCII删除和
     */
    public static int minimumDeleteSumByLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        // dp[i][j] 表示 s1[0...i-1] 和 s2[0...j-1] 的最长公共子序列的ASCII和
        int[][] dp = new int[m + 1][n + 1];

        // 开始填充二维数组
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1)){
                    //如果当前字符相等，则将当前字符的ASCII值加入dp[i][j]
                    dp[i][j] = dp[i-1][j-1]+s1.charAt(i-1);
                }else {
                    //如果当前字符不相等，则将dp[i-1][j]和dp[i][j-1]中的最大值加入dp[i][j]
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        // 计算最长公共子序列的ASCII和
        int totalSum = s1.chars().sum()+s2.chars().sum();
        // 返回最长公共子序列的ASCII和减去两倍公共部分的和
        return totalSum-2*dp[m][n];
    }
}
