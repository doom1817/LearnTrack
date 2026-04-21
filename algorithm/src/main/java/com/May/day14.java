package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/14/16:27
 * @description:
 *  力扣2784. 检查数组是否是好的
 */
public class day14 {
    public static void main(String[] args) {
        System.out.println(isGood(new int[]{2, 1, 3})); //false
    }
    private static boolean isGood(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++){
            if (nums[i] != i+1){
                return false;
            }
        }
        return nums[n-1] == n-1;
    }
}
