package com.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/06/10:35
 * @description:
 *  力扣15. 三数之和
 */
public class title2_1 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
    private static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++){
            if (i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int left = i+1, right = nums.length-1; // 双指针
            int target = -nums[i]; // 目标值
            while (left < right){
                if (nums[left] + nums[right] == target){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++; // 去重
                    while (left < right && nums[right] == nums[right-1]) right--; // 去重
                    left++;
                    right--;
                }else if (nums[left] + nums[right] < target){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return res;
    }
}
