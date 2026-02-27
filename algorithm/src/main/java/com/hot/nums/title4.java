package com.hot.nums;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/02/24/17:34
 * @description:
 * 力扣238.除了自身以外数组的乘积
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));
    }
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n]; // 创建一个长度为n的数组answer
        answer[0] = 1;
        for (int i = 1; i < n; i++){
            answer[i] = answer[i-1] * nums[i-1];
        }
        int suffixProduct = 1;
        for (int i = n-1; i >= 0; i--){
            answer[i] = answer[i] * suffixProduct;
            suffixProduct *= nums[i];
        }
        return answer;
    }
}
