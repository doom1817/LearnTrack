package com.DP.day6;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/03/22:18
 * @Description:
 * 力扣673. 最长递增子序列的个数
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
    }
    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length < 1)return 0;
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        //初始化
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }
                    else if (dp[j] + 1 == dp[i]){
                        cnt[i] += cnt[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) res += cnt[i];
        }
        return res;
    }
}
