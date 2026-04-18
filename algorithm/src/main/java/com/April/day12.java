package com.April;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/12/16:29
 * @description:
 *  力扣1320.二指输入的最小距离
 */
public class day12 {
    public static void main(String[] args) {
        System.out.println(minimumDistance("CAKE"));//3
    }
    public static int minimumDistance(String word) {
        int n = word.length();
        int[][] dist = new int[26][26];

        //预处理距离
        for (int i = 0; i < 26; i++){
            for (int j = 0; j < 26; j++){
                dist[i][j] = getDist(i , j);
            }
        }
        int[] dp = new int[26];
        Arrays.fill(dp, Integer.MAX_VALUE);

        //初始
        int prev =word.charAt(0)-'A';
        for (int i =0;i<26;i++)dp[i]=0;
        for (int i = 1; i < n; i++){
            int cur = word.charAt(i) - 'A';
            int[] temp = new int[26];
            Arrays.fill(temp, Integer.MAX_VALUE);
            for (int j = 0; j < 26; j++){
                if (dp[j] == Integer.MAX_VALUE) continue;
                //1. 同一只手
                temp[j] = Math.min(temp[j], dp[j] + dist[prev][cur]);
                //2. 用另一只手
                temp[prev] = Math.min(temp[prev], dp[j] + dist[j][cur]);
            }
            dp = temp;
            prev = cur;
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp) ans = Math.min(ans, i);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private static int getDist(int a, int b){
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
