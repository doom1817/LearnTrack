package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/16/10:28
 * @Descript:
 * 2466.统计好字符串的数目
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(new title3().countGoodStrings(3, 3,1,1));
    }
    private static final int MOD = 1000000007;
    /**
     * 计算满足条件的好字符串数量
     *
     * @param low   最小长度
     * @param high  最大长度
     * @param zero  添加 '0' 的次数
     * @param one   添加 '1' 的次数
     * @return 满足条件的好字符串数量
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        // 输入合法性校验
        if (low < 0 || high < 0 || zero <= 0 || one <= 0 || low > high) {
            return 0;
        }

        // 动态规划数组：dp[i] 表示长度恰好为 i 的好字符串的数目
        long[] dp = new long[high + 1];

        // 初始化
        dp[0] = 1; // 长度为 0 的好字符串有一种（空字符串）

        // 动态规划填表
        for (int i = 1; i <= high; i++) {
            // 以至少 zero 个 '0' 结尾
            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }
            // 以至少 one 个 '1' 结尾
            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
            // 注意：这里不需要前缀和。dp[i] 只依赖于 dp[i-zero] 和 dp[i-one]。
        }

        // 计算 [low, high] 范围内的总方法数
        long result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }
        return (int) result;
    }

}
