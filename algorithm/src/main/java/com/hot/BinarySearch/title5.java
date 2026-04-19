package com.hot.BinarySearch;

/**
 * @author: doom
 * @date: 2026/04/18/15:11
 * @description:
 *  力扣153. 寻找旋转排序数组中的最小值
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }
    private static int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        // 如果数组有序，则返回第一个元素
        if (nums[left] <= nums[right]){
            return nums[left];
        }
        while (left < right){
            int mid = left+(right-left)/2;
            if (nums[mid] < nums[right]){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return nums[left];
    }
}
