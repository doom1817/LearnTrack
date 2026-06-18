package com.interviews;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/06/17/09:38
 * @description:
 */
public class title1 {
    private static boolean wordBreak(String s, List<String> wordDict) {
        Set< String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
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
