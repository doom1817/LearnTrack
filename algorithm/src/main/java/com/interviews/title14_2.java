package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/10/10:25
 * @description:
 *  力扣172. 阶乘后的零
 */
public class title14_2 {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(5));//0
    }

    /**
     * 尾随零的个数 = 阶乘中因子 10 的个数 = 因子 5 的个数（因为因子 2 远多于 5）。
     * 核心公式：res = n/5 + n/25 + n/125 + ...（一直除到商为 0）。
     * @param n
     * @return
     */
    private static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
