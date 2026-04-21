package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/08/09:38
 * @description:
 *  力扣209. 长度最小的子数组
 */
public class title2_2 {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
    private static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        // 滑动窗口
        int left = 0;
        int currentSum = 0;
        for (int right = 0; right < n; right++){
            currentSum += nums[right];
            while (currentSum >= target){
                ans = Math.min(ans, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        // 返回结果
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
