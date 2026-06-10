package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/06/10/09:21
 * @description:
 * 力扣66. 加一
 */
public class title14_1 {
    private static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));// [1,2,4]
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
    }
}
