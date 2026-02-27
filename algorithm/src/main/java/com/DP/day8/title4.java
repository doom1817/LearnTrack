package com.DP.day8;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/08/19:46
 * @Description:
 * 力扣 1312. 让字符串成为回文串的最少插入次数
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz"));
        System.out.println(minInsertions("mbadm"));
        System.out.println(minInsertions("leetcode"));
    }

    public static int minInsertions(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int left = n - 2; left >= 0; left--) {
            for (int right = left + 1; right < n; right++) {
                if (chars[left] == chars[right]) {
                    dp[left][right] = dp[left + 1][right - 1];
                } else {
                    dp[left][right] = Math.min(dp[left + 1][right], dp[left][right - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

}
