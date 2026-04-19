package com.hot.BinarySearch;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/18/14:40
 * @description:
 *  力扣33. 搜索旋转排序数组
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
    }
    /**
     *  扩展思路：    原本是升序,这里给我的nuns是旋转后,
     * 能不能这么思考就是，比较旋转后与旋转前的数组，找到旋转点。
     */

    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int pivot = findPivot(nums); //找到旋转点
        int n  = nums.length;
        int left, right;
        // 判断target在旋转点左边还是右边
        if (target >= nums[pivot] && target <= nums[n-1]){
            left = pivot;
            right = n-1;
        }else {
            // target在旋转点右边
            left = 0;
            right = pivot-1;
        }

        while (left <= right){
            int mid = left+(right-left)/2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return -1;
    }
    private static int findPivot(int[] nums) {
        int left = 0, right = nums.length-1;
        if (nums[left] <= nums[right]){
            return left;
        }
        while (left<right){
            int mid = left+(right-left)/2;
            // 如果 mid 比 right 大，说明最小值在 mid 的右边
            if (nums[mid] > nums[right]){
                left=mid+1;
            }else {
                // 否则最小值在 mid 或者 mid 的左边
                right=mid;
            }
        }
        return left;
    }
}
