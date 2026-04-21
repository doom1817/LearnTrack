package com.hot.greedy;

/**
 * @author: doom
 * @date: 2026/04/24/09:28
 * @description:
 *  力扣55. 跳跃游戏
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4})); // true
    }
    public static boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--){
            if (i + nums[i] >= lastPos){
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
