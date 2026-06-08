package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/06/22:07
 * @description:
 * 力扣918. 环形子数组的最大和
 */
public class title12_1 {
    public static void main(String[] args) {
//        System.out.println(maxSubarraySumCircular(new int[]{1,-2,3,-2}));
        System.out.println(maxSubarraySumCircular(new int[]{5,-3,5}));
//        System.out.println(maxSubarraySumCircular(new int[]{3,-2,2,-3}));
    }
    private static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int curMax = nums[0];
        int minSum = nums[0];
        int curMin = nums[0];
        int total = nums[0];
        for (int i=1;i<n;i++){
            total += nums[i];

            curMax = Math.max(curMax + nums[i],nums[i]);
            maxSum = Math.max(maxSum,curMax);

            curMin = Math.min(curMin + nums[i],nums[i]);
            minSum = Math.min(minSum,curMin);
        }
        //特判：所有数都为负数
        if (maxSum< 0){
            return maxSum;
        }
        return Math.max(maxSum,total-minSum);
    }
}
