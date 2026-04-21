package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/06/10:15
 * @description:
 *  力扣167. 两数之和 II - 输入有序数组
 */
public class title1_3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
    private static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right){
            int sum = numbers[left] + numbers[right];
            if (sum == target){
                return new int[]{left + 1, right + 1};
            }else if (sum < target){
                left++;
            }else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
