package com.DP.day5;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/01/22:59
 * @Description:
 * 力扣115. 不同的子序列
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
    }
    public static int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // dp[j] 表示 t 的前 j 个字符在当前 s 前缀中作为子序列的出现次数
        long[] dp = new long[n + 1];
        dp[0] = 1;// 空字符串 t 总是匹配一次
        for (int i = 0; i < m; i++) {
            // 从右往左更新，避免覆盖未使用的 dp[j-1]
            for (int j = n; j > 0; j--) {
                //相等，则 dp[j] += dp[j-1]
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
                //不相等，则 dp[j] = dp[j]
            }
        }
        return (int) dp[n];
    }
}
