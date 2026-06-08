package com.June;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/06/06/20:50
 * @description:
 *  力扣
 */
public class day6 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(leftRightDifference(new int[]{10,4,8,3})));
    }
    private static int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int total = 0;
        for (int num:nums){
            total += num;
        }
        int leftSum = 0;
        int rightSum = total;
        for (int i=0;i<nums.length;i++){
            rightSum -= nums[i]; //右侧和减去当前元素
            answer[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i]; //左侧和加上当前元素
        }
        return answer;
    }
}
