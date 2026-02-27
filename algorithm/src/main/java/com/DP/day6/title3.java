package com.DP.day6;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/03/22:42
 * @Description:
 * 力扣646 . 最长数对链
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(findLongestChain(new int[][]{{1,2},{2,3},{3,4}}));
        System.out.println(findLongestChain2(new int[][]{{1,2},{7,8},{4,5}}));
    }
    /**
     *  传统的动态规划
     * 思路：
     * 1. 状态定义：dp[i] 表示以第 i 个数对结尾的最长数对链长度
     * 2. 状态转移方程：
     *      dp[i] = max(dp[i], dp[j] + 1)
     * 3. 初始化：
     *      dp[i] = 1
     * 4. 遍历顺序：
     *      按第一个元素排序
     * 5. 输出：
     *      返回 dp[n-1]
     **/
    public static int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length < 1) return 0;
        int n = pairs.length;
        //按第一个元素排序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (pairs[i][0] > pairs[j][1]){
                    //dp[i] 以第 i 个数对结尾的最长数对链长度
                    //pairs[i][0] 第 i 个数对的起始值 c
                    // pairs[j][1] 第 j 个数对的结束值 b
                    //取当前长度与通过 j 连接后的长度的最大值
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLen = 0;
        for (int len: dp){
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

    /**
     * 贪心 + 排序（O(n log n)）
     * 思路：
     * 1. 状态定义：dp[i] ：以第 i 个数对结尾的最长数对链长度
     * 2. 状态转移方程：
     *      dp[i] = max(dp[i], dp[j] + 1)
     * 3. 输出：
     *      返回 dp[n-1]
     **/
    private static int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int count=0,end = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (end < pair[0]) {
                end = pair[1];
                count++;
            }
        }
        return count;
    }
}
