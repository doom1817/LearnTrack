package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/20/09:40
 * @description:
 *  力扣238. 除自身以外数组的乘积
 */
public class title5_5 {
    private static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--){
            answer[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return answer;
    }
}
