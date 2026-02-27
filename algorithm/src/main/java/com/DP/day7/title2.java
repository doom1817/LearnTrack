package com.DP.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/05/10:34
 * @Description:
 * 力扣 1027. 最长等差数列
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(longestArithSeqLength(new int[]{3,6,9,12}));
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int longestArithSeqLength(int[] nums) {
        // 获取数组长度
        int n = nums.length;
        // 使用哈希表存储
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        int maxLen = 2;
        //初始化每个位置的Map
        for (int i = 0; i < n; i++){
            dp.put(i, new HashMap<>());
        }
        /**
         * 外层循环遍历数组，对于每个位置i，外层循环遍历数组的剩余部分，得到以nums[i]为开头的最长等差子序列长度。
         * 内层循环遍历数组的剩余部分，计算当前位置的差值，并更新最长等差子序列长度。
         * diff 表示当前位置的差值，len 表示以nums[i]为开头的最长等差子序列长度。
         * 获取以 i 为结尾、差值为 diff 的最长子序列长度，默认为 1
         * 更新以 j 为结尾、差值为 diff 的最长子序列长度
         * 更新最长等差子序列长度
         */
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = nums[j] - nums[i];
                int len = dp.get(i).getOrDefault(diff, 1) + 1;
                dp.get(j).put(diff, len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    private static int longestArithSeqLength2(int[] nums){
        int ans = 0;
        int[][] dp =new int[nums.length][1005];
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++)
            {
                int d = nums[j] - nums[i] + 500;
                dp[j][d] = Math.max(dp[j][d], dp[i][d] + 1);
                ans = Math.max(ans, dp[j][d]);
            }
        }
    return ans + 1;
    }

}
