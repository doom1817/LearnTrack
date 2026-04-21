package com.hot.dp;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/30/11:09
 * @description: 力扣416. 分割等和子集
 */
public class title10 {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));// true
    }

    /**
     * 分成两个元素和相等的子集。-> 总和是偶数
     *
     * @param nums
     * @return
     */
    private static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false; // 总和是奇数，无法平分
        int target = sum / 2; // 单个子集目标和
        boolean[] dp = new boolean[target + 1];
        for (int num : nums) {
            if (num > target) continue;
            for (int j = target; j >= num; j--) {
                if (dp[j - num]) dp[j] = true;
            }
            if (dp[target]) return true;
        }
        return false;
    }
}
