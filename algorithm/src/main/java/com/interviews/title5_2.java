package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/19/09:35
 * @description:
 *  力扣122. 买卖股票的最佳时机  II
 */
public class title5_2 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));//7
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));//4
    }
    static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length ; i++){
            if (prices[i] > prices[i-1]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }
}
