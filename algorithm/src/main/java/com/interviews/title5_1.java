package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/19/09:13
 * @description:
 *  力扣 80. 删除排序数组中的重复项 II
 */
public class title5_1 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));// 5, nums = [1,1,2,2,3]
    }

    static  int removeDuplicates(int[] nums) {
        int stackSize = 2;
        for (int j = 2; j < nums.length; j++){
            if (nums[j] != nums[stackSize - 2]){
                nums[stackSize++] = nums[j];
            }
        }
        return Math.min(nums.length, stackSize);
    }
}
