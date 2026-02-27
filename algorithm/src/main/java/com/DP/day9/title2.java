package com.DP.day9;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/09/13:40
 * @Description:
 * 力扣714.买卖股票的最佳时机含手续费
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(maxProfit1(new int[]{1,3,2,8,4,9}, 2));
        System.out.println(maxProfit1(new int[]{1,3,7,5,10,3}, 3));
    }
    /**
     * 递推二维dp
     * */
    private static int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2]; //dp[i][0]表示第i天不持有股票，dp[i][1]表示第i天持有股票
        //初始化
        dp[0][0] = 0; //不持股：啥也没干
        dp[0][1] = -prices[0]; // 持股：买入第一天
        for (int i = 1; i < prices.length; i++){
                //要么昨天就不持股，要么今天卖出（扣手续费）
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            //要么昨天就持股，要么今天买入
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[prices.length-1][0];
    }
    /**
     *  变量替换
     * */
    private static int maxProfit1(int[] prices, int fee) {
        if(prices == null || prices.length == 0) return 0;
        int hold = -prices[0]; // 持股时最大利润
        int free = 0; // 不持股时最大利润
        for (int i = 1; i < prices.length; i++){
            int new_free= Math.max(free, hold+prices[i] - fee);
            int new_hold = Math.max(hold, free - prices[i]);

            free = new_free;
            hold = new_hold;
        }
        return free;
    }
}
