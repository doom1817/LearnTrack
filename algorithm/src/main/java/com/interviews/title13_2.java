package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/08/09:38
 * @description:
 *  力扣 162. 寻找峰值
 */
public class title13_2 {
    private static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            //每次与右侧元素对比
            if (nums[mid] > nums[mid + 1]){
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,3,1}));//2
    }
}
