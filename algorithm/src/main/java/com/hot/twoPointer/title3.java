package com.hot.twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/19:21
 * @Description:
 * 力扣15. 三数之和
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{0, 1, 1}));
        System.out.println(threeSum(new int[]{0, 0, 0}));
        System.err.println(threeSum(new int[]{-2, 0, 0, 2, 2}));
    }
    public static List<List<Integer>> threeSum(int[] nums){
        int numsLength = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numsLength-2; i++){
            if (i > 0 && nums[i] == nums[i-1]){continue;}// 去重：如果当前数字和前一个数字相同，则跳过，避免重复的三元组
            int left = i + 1;
            int right = numsLength - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 关键修复点：在找到一个解后，同时移动 left 和 right，并跳过所有重复值
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    // 移动到下一个不同的元素
                    left++;
                    right--;
                }
                if (sum <= 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }
}
