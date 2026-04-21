package com.hot.dp;

/**
 * @author: doom
 * @date: 2026/04/28/11:15
 * @description:
 *  力扣198. 打家劫舍
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
    }
    public static int rob(int[] nums) {
        int prev =0, curr = 0; // prev: 上次选择，curr: 当前选择
        for (int num : nums){
            int temp = curr;
            curr = Math.max(prev + num, curr);
            prev = temp;
        }
        return curr;
    }
}
