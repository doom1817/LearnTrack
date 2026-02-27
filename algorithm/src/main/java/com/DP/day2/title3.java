package com.DP.day2;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/17/21:47
 * @Description:
 * 力扣120 三角形最小路径和
 * 题目要求：给定一个三角形，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上（即当前行的下标i可以移动到下一行的下标i或i+1）
 * 解题思路：使用动态规划自底向上方法，优化空间复杂度为O(n)
 */
public class title3 {
    public static void main(String[] args) {
        // 测试用例：三角形最小路径和应为11 (2+3+5+1)
        System.out.println(minimumTotal(List.of(
                List.of(2),
                List.of(3,4),
                List.of(6,5,7),
                List.of(4,1,8,3)
        )));
    }
    /**
     * 计算三角形自顶向下的最小路径和
     * 使用动态规划自底向上方法，空间复杂度O(n)
     *
     * @param triangle 三角形数据，每行元素个数递增
     * @return 最小路径和
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        // 特殊情况处理：空三角形直接返回0
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }

        int n = triangle.size(); // 三角形的行数
        // dp数组初始化：dp[j]表示从底部到达当前行的第j个位置的最小路径和
        // 初始化为三角形的最后一行，因为从底部开始计算
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = triangle.get(n-1).get(i);
        }

        // 自底向上动态规划：从倒数第二行开始向上遍历
        for(int i = n - 2; i >= 0; i--){
            // 遍历当前行的每个元素
            for(int j = 0; j <= i; j++){
                // 状态转移方程：当前值 + 下一层相邻两个路径中的较小值
                // dp[j]和dp[j+1]分别代表下一层相邻两个位置的最小路径和
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        // 最终dp[0]存储的就是从顶部到底部的最小路径和
        return dp[0];
    }
}
