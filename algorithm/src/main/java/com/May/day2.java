package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/22/10:59
 * @description:
 *  力扣788.旋转数字
 */
public class day2 {
    public static void main(String[] args) {
        System.out.println(rotatedDigits(10));// 4
    }

    /**
     *  反正数只有2，5，6，9。
     * @param n
     * @return
     */
    public static int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++){
            if (isGood(i))count++;
        }
        return count;
    }
    private static boolean isGood(int n){
        boolean flag = false;
        while (n > 0){
            int digit = n % 10;
            if (digit == 3 || digit == 4 || digit == 7) return false;
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9)flag=true;
            n/=10;
        }
        return flag;
    }
}
