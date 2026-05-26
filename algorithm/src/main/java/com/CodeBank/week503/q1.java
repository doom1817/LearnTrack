package com.CodeBank.week503;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/24/21:10
 * @description:
 *
 */
public class q1 {
    public static int[] limitOccurrences(int[] nums, int k){
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] temp = new int[nums.length];
        int index = 0;
        int count = 1;
        temp[index++] = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i - 1]){
                count++;
            }else {
                count = 1;
            }
            if (count <= k){
                temp[index++] = nums[i];
            }
        }
        return Arrays.copyOf(temp, index);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(limitOccurrences(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }// [1, 1, 2, 2,3]
}
