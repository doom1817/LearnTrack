package com.hot.dp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/18/16:24
 * @Description:
 *  力扣322. 零钱兑换
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));

    }
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        if (amount == 0) {return 0;}
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount+1;
        }
        for (int i = 1; i <= amount; i++){
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}
