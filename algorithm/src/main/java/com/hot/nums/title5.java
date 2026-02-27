package com.hot.nums;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/02/24/17:48
 * @description:
 * 力扣41. 缺失的第一个正数
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++){
            while (nums[i]>0 && nums[i] <= n && nums[nums[i]-1] != nums[i]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int j = 0; j < n; j++){
            if (nums[j] != j+1){
                return j+1;
            }
        }
        return n + 1;
    }
}
