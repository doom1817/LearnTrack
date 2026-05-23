package com.May;

/**
 * @author: doom
 * @date: 2026/05/22/10:36
 * @description:
 *  力扣396. 旋转函数
 */
public class day1 {
    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[]{4,3,2,6}));
    }
    private static int maxRotateFunction(int[] nums) {
        int n = nums.length;
        if (n <= 1){
            return 0;
        }
        int sum = 0;
        int f = 0;
        // 计算数组总和与初始 F(0) 的值
        for (int i = 0; i < n; i++){
            sum += nums[i];
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i >= 1; i--){
            f = f + sum - n * nums[i]; //F(k) = F(k-1) + sum - n * nums[n-k]
            res = Math.max(res, f);
        }
        return res;
    }
}
