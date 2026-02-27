package com.DP.day4;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/31/21:28
 * @Description:
 * 力扣5。最长回文子串
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    /**
     * 动态规划
     * 状态转移方程：dp[i][j] = s[i]==s[j] && dp[i+1][j-1]
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j]表示从i到j的字符串是否是回文串
        String longest = "";
        /**
         * 外层循环 固定子串结束位置， 内层循环 移动子串的起始位置
         * 通过dp[i][j]记录子串是否是回文串
          */
        for (int j=0;j<n;j++){
            for (int i=0;i<=j;i++){
                if (s.charAt(i)==s.charAt(j)){ //首尾字符相等
                    if (j-i<2){
                        dp[i][j]=true;
                    }
                    else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if (dp[i][j] && (j-i+1)>longest.length()) {
                    longest = s.substring(i,j+1);
                }
            }
        }

        return longest;
    }
}
