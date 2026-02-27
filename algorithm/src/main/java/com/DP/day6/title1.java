package com.DP.day6;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/03/22:07
 * @Description:
 * 力扣300. 最长递增子序列
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length < 1) return 0;
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = Integer.MIN_VALUE;
        int res = 0;
        for (int num : nums) {
            for (int j = res; j >= 0; j--) {
                if (num > dp[j]) {
                    dp[j + 1] = num;
                    if (j == res) res++;
                    break;
                }
            }
        }
        return res;
    }
}
