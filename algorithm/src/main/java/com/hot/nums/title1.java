package com.hot.nums;

/**
 * @author: doom
 * @date: 2026/02/24/15:40
 * @description:
 * 力扣53. 最大子数组和
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
    }
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for (int num:nums){
            if (sum > 0){
                sum += num;
            }
            else {
                sum = num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
