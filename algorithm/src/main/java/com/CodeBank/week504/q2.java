package com.CodeBank.week504;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/31/14:13
 * @description:
 */
public class q2 {
    public static void main(String[] args) {
        System.out.println(maximumSaleItems(new int[][]{{6, 2}, {2, 6}, {3, 4}}, 9));//4
        System.out.println(maximumSaleItems(new int[][]{{2, 4}, {3, 2}, {4, 1},{6,4},{12,4}}, 8));//10
    }

    /**
     * 1.一个物品的免费物品只能获取一次
     * 2.
     * @param items 描述物品的数组
     * @param budget 预算
     * @return
     */
    private static int maximumSaleItems(int[][] items, int budget) {
        int  n = items.length;
        int[] factor = new int[n];
        int[] price = new int[n];
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            factor[i] = items[i][0];
            price[i] = items[i][1];
            minPrice = Math.min(minPrice, items[i][1]);
        }
        // 计算每个物品的免费物品
        int[] free = new int[n];
        for (int i = 0; i < n; i++){
            int cnt  = 0;
            for (int j = 0; j < n; j++){
                if (i != j && factor[j] % factor[i] == 0){
                    cnt++;
                }
            }
            free[i] = cnt;
        }
        // 01背包：dp[c] 表示花费恰好 c 时，通过首次购买获得的最大物品数
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++){
            int cost = price[i];
            int gain = 1+ free[i];
            for (int j = budget; j >= cost; j--){
                if (dp[j-cost] != -1){
                    dp[j] = Math.max(dp[j], dp[j-cost] + gain);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++){
            if (dp[i] != -1){
                int extra = (budget - i) / minPrice;
                ans = Math.max(ans, dp[i] + extra);
            }
        }
        return ans;
    }
}
