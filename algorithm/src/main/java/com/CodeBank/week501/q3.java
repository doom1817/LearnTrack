package com.CodeBank.week501;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/10/19:12
 * @description:
 */
public class q3 {

    public static void main(String[] args) {
        System.out.println(minArraySum(new int[]{3,6,2}));// 7
        System.out.println(minArraySum(new int[]{4,2,8,3}));// 9
    }
    private static long minArraySum(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (nums[j] % nums[i] == 0){
                    // 将大的数替换为小的数
                    nums[j] = nums[i];
                }
            }
        }
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
