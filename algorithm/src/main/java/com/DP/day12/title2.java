package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/16/10:19
 * @Description:
 * 力扣322. 零钱兑换
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11));
    }
    //重点 ： 个数最少 但是要到达总金额
    public static int coinChange(int[] coins, int amount){
        int[] dp = new int[amount+1];
        if (amount == 0) return 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++){
            for (int coin : coins) {
                //如果当前银币面额小于等于当前总金额
                if (coin <= i) {
                    //如果dp[i-coin] 是可以到达这里的
                    if (dp[i - coin] != Integer.MAX_VALUE) {
                        //  在i-coin 的最少硬币数量基础上 +1
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
