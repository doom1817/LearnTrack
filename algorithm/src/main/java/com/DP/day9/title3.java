package com.DP.day9;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/09/14:13
 * @Description:
 * 力扣123. 买卖股票的最佳时机 III
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }
    /*
    前后缀+动态规划
     */
    private static int maxProfit(int[] prices) {
        int n = prices.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1,minPrice=prices[0]; i < n; i++){
            left[i] = Math.max(left[i-1], prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        for (int i = n-2,maxPrice=prices[n-1]; i >= 0; i--){
            right[i] = Math.max(right[i+1], maxPrice - prices[i]);
            maxPrice = Math.max(maxPrice, prices[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, left[i] + right[i]);
        }
        return ans;
    }

    /*
    状态机
     */
    private static int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        for (int price : prices){
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }
    /*
    滚动状态数组 + 倒序更新
     */
    private static int maxProfit3(int[] prices) {
        final int k = 2;
        // dp[i][0] 表示第 i 轮不持有股票，dp[i][1] 表示第 i 轮持有股票
        int[][] dp = new int[k+1][2];
        // 初始化
        for (int i = 0; i <= k; i++){
            dp[i][1] = Integer.MIN_VALUE/2;
        }
        dp[0][0] = 0;

        for (int p:prices){
            for (int i = k; i >= 1; i--){
                dp[i][0] =Math.max(dp[i][0], dp[i][1] + p); //卖出，完成第i笔
                dp[i][1] = Math.max(dp[i][1], dp[i-1][0] - p); //买入,基于i-1笔完成
            }
        }
        return dp[k][0];
    }
}
