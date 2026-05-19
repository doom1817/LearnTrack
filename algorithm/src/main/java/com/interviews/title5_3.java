package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/19/09:56
 * @description:
 *  力扣 45. 跳跃游戏 II
 */
public class title5_3 {
    public static void main(String[] args) {
        System.out.println(Jump(new int[]{2,3,1,1,4}));// 2
    }
    static int Jump(int[] nums){
        int n = nums.length;
        if (n==0) return 0;
        int minCount = 0;
        int maxReach = 0;
        int cur = 0 ;
        for (int i = 0; i < n-1; i++){
            maxReach = Math.max(maxReach,i+nums[i]);
            if (cur == i){
                minCount++;
                cur = maxReach;
                if (cur >= n-1){
                    break;
                }
            }
        }
        return minCount;
    }
}
