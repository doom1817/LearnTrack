package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/23/14:16
 * @description:
 *  力扣1752. 检查数组是否经排序和轮转得到
 */
public class day23 {
    public static void main(String[] args) {
        System.out.println(check(new int[]{3,4,5,1,2}));
    }
    //最多只有两段递增段
    private static boolean check(int[] nums){
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++){
            if (nums[i]>nums[i+1]%n){
                count++;
            }
        }
        return count<=1;
    }
}
