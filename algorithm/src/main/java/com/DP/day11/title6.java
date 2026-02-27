package com.DP.day11;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/15/13:36
 * @Description:
 * 力扣474 一和零
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
    /**
     * 在给定的二进制字符串数组中，找到能由最多 m 个 0 和 n 个 1 构成的字符串的最大数量。
     *
     * 这是一个二维 0-1 背包问题。每个字符串被视为一个物品，其“重量”是它包含的 0 和 1 的数量，
     * 背包的容量是 m 个 0 和 n 个 1。目标是在不超过背包容量的前提下，放入尽可能多的物品（字符串）。
     *
     * @param strs 输入的二进制字符串数组
     * @param m    可用的 0 的最大数量
     * @param n    可用的 1 的最大数量
     * @return     满足条件的字符串的最大数量
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 表示使用 i 个 0 和 j 个 1，最多可以组成的字符串数量
        // 这是一个二维 0-1 背包问题，背包容量有两个维度：0的数量和1的数量
        int[][] dp = new int[m + 1][n + 1];

        // 遍历每个字符串，将其视为一个待放入背包的物品
        for (String str : strs) {
            // 统计当前字符串中 0 和 1 的个数
            // count[0] 存储 0 的个数，count[1] 存储 1 的个数
            int[] count = new int[2];
            for (char c : str.toCharArray()) {
                count[c - '0']++; // c - '0' 将字符 '0' 或 '1' 转换为数字 0 或 1
            }

            // 关键：从后往前遍历背包容量
            // 这是为了防止同一个字符串被重复使用（0-1背包的特性）
            // 如果从前往后遍历，dp[i][j] 在计算时可能会用到本轮已更新过的 dp[i-count[0]][j-count[1]]
            for (int i = m; i >= count[0]; i--) { // i 表示当前可用的 0 的数量
                for (int j = n; j >= count[1]; j--) { // j 表示当前可用的 1 的数量
                    // 状态转移方程：
                    // dp[i][j] = max(
                    //     dp[i][j],                           // 不选择当前字符串
                    //     dp[i - count[0]][j - count[1]] + 1  // 选择当前字符串
                    // )
                    //
                    // dp[i][j]: 在拥有 i 个 0 和 j 个 1 的情况下，不使用当前字符串 str，
                    //           所能达到的最大字符串数量（继承上一轮的状态）
                    //
                    // dp[i - count[0]][j - count[1]] + 1: 在拥有 i 个 0 和 j 个 1 的情况下，
                    //                                    选择使用当前字符串 str。
                    //                                    - 需要消耗 count[0] 个 0 和 count[1] 个 1
                    //                                    - 剩余容量为 (i - count[0], j - count[1])
                    //                                    - 此时的最大数量 = 使用剩余容量能组成的最大数量 + 1 (当前字符串)
                    dp[i][j] = Math.max(
                            dp[i][j],                           // 不选择当前字符串 str
                            dp[i - count[0]][j - count[1]] + 1  // 选择当前字符串 str
                    );
                }
            }
        }

        // dp[m][n] 表示使用 m 个 0 和 n 个 1，最多可以组成的字符串数量
        return dp[m][n];
    }
}
