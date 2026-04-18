package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/15/10:16
 * @description:
 * 力扣131. 分割回文串
 */
public class title7 {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.isEmpty()){
            return res;
        }
        int n =s.length();
        // 预处理
        boolean[][] dp = new boolean[n][n];
        for (int right = 0; right < n; right++){
            for (int left = right; left >= 0; left--){
                if (s.charAt(right) == s.charAt(left) && (right - left < 2 || dp[left +1][right -1])){
                    dp[left][right] = true;
                }
            }
        }
        backtracking(s,0,new ArrayList<>(),res,dp);
        return res;
    }
    private static void backtracking(String s, int startIndex, List<String> path, List<List<String>> res,boolean[][] dp) {
        if (startIndex == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = startIndex; end < s.length(); end++) {
            if (!dp[startIndex][end]){
                continue;
            }
            path.add(s.substring(startIndex, end +1));
            backtracking(s, end +1,path,res,dp);
            path.remove(path.size()-1);
        }
    }
}

