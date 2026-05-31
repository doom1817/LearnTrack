package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/29/09:10
 * @description:
 *  力扣3300. 替换为数位和以后的最小元素
 */
public class day29 {
    public static void main(String[] args) {
        System.out.println(minElement(new int[]{10,12,13,14}));//10
    }

    private static int minElement(int[] nums){
        return Arrays.stream(nums)
                .map(n -> {
                    int s = 0;
                    while (n > 0) {
                        s += n % 10;
                        n /= 10;
                    }
                    return s;
                })
                .min()
                .getAsInt();
    }
}
