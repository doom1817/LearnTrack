package com.May;

/**
 * @author: doom
 * @date: 2026/05/22/09:18
 * @description:
 *  力扣33. 搜索旋转排序数组
 */
public class day22 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
    }
    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int point = findPoint(nums);
        int n = nums.length;
        int left = 0, right = n - 1;
        if (target >= nums[point] && target <= nums[right]){
            left = point; // 判断target的位置
        }else {
            left = 0;
            right = point - 1;
        }
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //关键是升序排列：旋转点的特征是第一个小于左边的数
    private static int findPoint(int[] nums){
        int left = 0, right = nums.length - 1;
        if (nums[left] <= nums[right]){
            return left;
        }
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }
}
