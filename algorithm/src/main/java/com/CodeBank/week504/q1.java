package com.CodeBank.week504;

/**
 * @author: doom
 * @date: 2026/05/31/14:08
 * @description:
 */
public class q1 {
    private static int digitFrequencyScore(int n) {
        int score = 0;
        while (n > 0) {
            score += n % 10;
            n /= 10;
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(digitFrequencyScore(101));
    }
}
