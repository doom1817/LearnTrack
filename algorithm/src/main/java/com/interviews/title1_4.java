package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/06/10:28
 * @description:
 *  力扣11.盛最多水的容器
 */
public class title1_4 {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    private static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            int area =Math.min(height[left], height[right]*(right-left));
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                left++;
            }else {
                right--;
            }
        }
        return max;
    }
}
