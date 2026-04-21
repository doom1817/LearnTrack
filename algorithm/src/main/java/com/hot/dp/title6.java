package com.hot.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/04/28/11:22
 * @description:
 *  力扣 139. 单词拆分
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(word("leetcode", List.of("leet", "code")));
    }
    private static boolean word(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 1; i <= n; i++){
            for (int j = i-1; j >= 0; j--){
                if (dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
