package com.DP.day9;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/09/12:36
 * @Description:
 * 力扣309. 最佳买卖股票时机含冷冻期
 * */
public class title1 {
    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{1,2,3,0,2}));
        System.out.println(maxProfit2(new int[]{1}));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int rest=0; //表示不持有股票
        int hold = -prices[0]; //购入-持有，利润是负值，表示花费了price[0]的成本
        int sold = 0; //冷静期 - 刚卖出去

        for(int i = 1; i < prices.length; i++){
            int new_rest = Math.max(rest, sold); //计算当前不持股且不在冷冻期状态下的最大利润。
            int new_hold = Math.max(hold, rest - prices[i]);//计算当前持股状态下的最大利润。
            int new_sold = hold + prices[i]; //卖出：前一天必须是持股状态，今天卖出股票。
            rest = new_rest;
            hold = new_hold;
            sold = new_sold;
        }
        return Math.max(rest, sold);
    }
    /**
     * 递推二维dp
     * */
    private static int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+2][2];
        dp[1][1] = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            dp[i+2][0] = Math.max(dp[i+1][0], dp[i+1][1] + prices[i]); //不持股状态更新
            dp[i+1][1] = Math.max(dp[i+2][1], dp[i][0] - prices[i]); //持股状态更新 +2对应今天刚更新的
        }
        return dp[n+1][0];
    }
    /*
    状态机：
        rest (不持股且不在冷冻期)
      ├── 保持 rest 状态
      └── 从 sold 状态转移（冷冻期结束）

    hold (持股)
      ├── 保持 hold 状态
      └── 从 rest 状态转移（买入股票）

    sold (刚卖出股票，冷冻期)
      └── 从 hold 状态转移（卖出股票）
     */
}
