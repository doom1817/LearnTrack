package com.CodeBank.week501;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/10/18:15
 * @description:
 */
public class q1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(concatWithReverse(new int[]{1,2,3}))); // [1,2,3,3,2,1]
    }

    private static int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n*2];
        for (int i=0;i<2*n;i++){
            //开始逆序添加
            if (i>=n){
                ans[i] = nums[2*n - 1-i];
            }else {
                ans[i]=nums[i];
            }
        }
        return ans;
    }
}
