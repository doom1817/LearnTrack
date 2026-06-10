package com.interviews;

/**
 * @author: doom
 * @date: 2026/06/10/10:42
 * @description:
 *  力扣50. Pow(x, n)
 */
public class title14_3 {
    public static void main(String[] args) {
        System.out.println(myPow(2.0, 10)); // 1024.0
        System.out.println(myPow(2.0, -2)); // 0.25
    }
    private static double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        long absN = Math.abs((long) n);
        double result = 1.0;
        double base = x;

        while (absN > 0) {
            if ((absN & 1) == 1) {
                result *= base;
            }
            base *= base;
            absN >>= 1;
        }

        return n > 0 ? result : 1.0 / result;
    }
}
