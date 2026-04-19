package com.hot.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/18/14:25
 * @description:
 *  力扣34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 8))); //[3,4]
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 6))); //[-1,-1]
        System.out.println(Arrays.toString(searchRange(new int[]{}, 0))); //[-1,-1]
    }
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        int leftBorder = findFirstGreaterOrEqual(nums, target);
        int rightBorder = findFirstGreaterOrEqual(nums, target+1)-1;
        if (leftBorder>= nums.length || nums[leftBorder] != target){
            return new int[]{-1, -1};
        }
        return new int[]{leftBorder, rightBorder};
    }
    private static int findFirstGreaterOrEqual(int[] nums, int target){
        int left = 0, right = nums.length-1;
        int ans = nums.length;
        while (left<=right){
            int mid =(left+right) >>> 1;
            if (nums[mid] >= target){
                ans = mid;
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return ans;
    }
}
