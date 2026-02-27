package com.DP.day10;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/10/10:50
 * @Description:
 * 力扣188. 买卖股票的最佳时机 IV
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2, 4, 1}));
        System.out.println(maxProfit(7, new int[]{3, 2, 6, 5, 0, 3}));
        System.out.println(maxProfit(2, new int[]{1, 2, 3, 4, 5}));
    }

    private static int maxProfit(int k, int[] prices) {
        int n = prices.length;// 获取价格数组的长度
        if (n == 0 || k == 0) return 0;// 如果没有价格数据或交易次数为0，直接返回0
        // 当交易次数大于等于天数的一半时，退化为贪心策略（无限交易）
        if (k >= n / 2) {
            int maxP = 0;
            for (int i = 1; i < n; i++) {
                maxP += Math.max(0, prices[i] - prices[i - 1]);// 累加所有上涨日的利润
            }
            return maxP;
        }
        // 定义状态数组：hold[i] 表示第i次交易后持有股票的最大利润，rest[i] 表示第i次交易后不持有股票的最大利润
        int[] hold = new int[k + 1];
        int[] rest = new int[k + 1];
        // 初始化第一天的状态
        for (int t = 1; t <= k; t++) {
            hold[t] = -prices[0];// 第一天买入股票，利润为负的价格
            rest[t] = 0;// 第一天不持有股票，利润为0
        }
// 动态规划填表：遍历每一天和每一次交易
        for (int day = 1; day < n; day++) {
            for (int trans = k; trans >= 1; trans--) {// 逆序遍历交易次数，避免覆盖未更新的数据
                // 更新持有股票状态：要么继续持有，要么当天买入（前提是已完成trans-1次交易）
                hold[trans] = Math.max(hold[trans], rest[trans - 1] - prices[day]);
                // 更新不持有股票状态：要么继续不持有，要么当天卖出（前提是当前持有股票）
                rest[trans] = Math.max(rest[trans], hold[trans] + prices[day]);
            }
        }

        return rest[k];// 返回最多完成k次交易后的最大利润
    }

}
