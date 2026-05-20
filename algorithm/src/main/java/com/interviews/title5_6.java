package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/20/09:46
 * @description:
 *  力扣 135. 分发糖果
 */
public class title5_6 {
    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,0,2}));//5
        System.out.println(candy(new int[]{1,2,2}));//4
    }

    /**
     *  解题条件：
     *  1.每个人至少1个糖果
     *  2.相邻的评分高的人会获得更多的糖果
     *  3.实例发现如果评分相同，ratings = [1,2,2] 就是 1，2，1。没有比较就是1
     * @param ratings
     * @return
     */
    private static int candy(int[] ratings){
        int n = ratings.length;
        int[] res = new int[n];
        // 初始化每人至少1个糖果
        Arrays.fill(res, 1);

        // 从左到右遍历：处理右侧评分更高的情况
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                res[i] = res[i - 1] + 1;
            }
        }

        // 从右到左遍历：处理左侧评分更高的情况，取最大值保证双向约束
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                res[i] = Math.max(res[i], res[i + 1] + 1);
            }
        }
        return Arrays.stream(res).sum();
    }
}
