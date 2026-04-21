package com.hot.dp;

/**
 * @author: doom
 * @date: 2026/04/30/10:38
 * @description:
 *  力扣300.最长递增子序列
 */
public class title8 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
    }
    //满足两个条件:递增、子序列
    private static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <1) return 0;
        int n = nums.length;
        int[] dp = new int[n+1];
        int[] tails = new int[n];
        int len = 0;
        for(int num:nums){
            int left = 0, right = len;
            while (left < right){
                int mid = left + (right - left)/2;
                if (tails[mid] < num){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == len){
                len++;
            }
        }
        return len;
    }
}
