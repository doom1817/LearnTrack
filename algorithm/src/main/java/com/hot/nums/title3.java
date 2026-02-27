package com.hot.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/02/24/17:09
 * @description:
 * 力扣189. 轮转数组
 */
public class title3 {
    public static void main(String[] args) {
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        rotate(new int[]{-1, -100, 3, 99}, 2);

    }
    //向右轮转k步
    public static void rotate(int[] nums, int k){
        int len = nums.length;
        int index = k % len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, index - 1);
        reverse(nums, index, len - 1);
    }

    private static void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
