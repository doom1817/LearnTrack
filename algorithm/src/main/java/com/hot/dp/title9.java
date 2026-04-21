package com.hot.dp;

/**
 * @author: doom
 * @date: 2026/04/30/10:49
 * @description:
 *  力扣152. 乘积最大子数组
 */
public class title9 {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4})); // 6
    }
    // 连续子数组，非空，乘积最大
    private static int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++){
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(max * curr, min * curr));
            min  = Math.min(curr, Math.min(max * curr, min * curr));
            max = tempMax;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
