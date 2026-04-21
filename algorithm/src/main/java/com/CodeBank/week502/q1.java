package com.CodeBank.week502;

/**
 * @author: doom
 * @date: 2026/05/17/15:04
 * @description:
 */
public class q1 {
    public static void main(String[] args) {
        System.out.println(isAdjacentDiffAtMostTwo("132"));
        System.out.println(isAdjacentDiffAtMostTwo("129"));
    }
    private static boolean isAdjacentDiffAtMostTwo(String s) {
        for (int i = 1; i < s.length(); i++) {
            char prevChar = s.charAt(i-1);
            char currChar = s.charAt(i);

            int prevNum = prevChar - '0';
            int currNum = currChar - '0';
            int diff = Math.abs(prevNum - currNum);
            if (diff > 2){
                return false;
            }
        }
        return true;
    }
}
