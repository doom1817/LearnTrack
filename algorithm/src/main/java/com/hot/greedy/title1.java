package com.hot.greedy;

/**
 * @author: doom
 * @date: 2026/04/24/09:20
 * @description:
 *  力扣121. 买卖股票的最佳时机
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
    private static  int maxProfit(int[] prices){
        int minProfit = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length ; i++){
            if (prices[i] < minProfit){
                minProfit = prices[i];
            }
            else {
                maxProfit = Math.max(maxProfit, prices[i]-minProfit);
            }
        }
        return maxProfit;
    }
}
