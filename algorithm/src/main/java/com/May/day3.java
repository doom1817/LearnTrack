package com.May;

/**
 * @author: doom
 * @date: 2026/05/03/21:18
 * @description:
 *  力扣796：旋转字符串
 */
public class day3 {
    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));// true
    }
    private static boolean rotateString(String s, String goal) {
        if (s == null || goal == null) return false;
        if (s.length() != goal.length()) return false;
        if (s.isEmpty()) return true;

        return (s + s).contains(goal);
    }
}
