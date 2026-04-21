package com.CodeBank.Huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: doom
 * @date: 2026/04/28/10:46
 * @description:
 */
public class t3 {
    public static void main(String[] args) {
        long E1 = 10;
        long[] damage1 = {9, 5, 5};
        long[] reward1 = {2, 1, 1};
        System.out.println(maxLevels(E1, damage1, reward1));
    }

    /**
     *  计算最多能成功挑战的关卡数
     * @param E 初始能量
     * @param damage 关卡的伤害
     * @param reward 关卡的奖励
     * @return 最多能成功挑战的关卡数
     */
    public static int maxLevels(long E, long[] damage, long[] reward) {
        int n = damage.length;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = E;
        // 遍历每一个关卡
        for (int i = 0; i < n; i++) {
            long curDamage = damage[i];
            long curReward = reward[i];

            // 倒序遍历挑战的关卡数量，典型的 0-1 背包降维处理
            // 最多可能挑战的关卡数不会超过 i + 1
            for (int j = i + 1; j >= 1; j--) {
                // 只有当挑战 j-1 个关卡后的剩余能量严格大于当前关卡的 damage 时，才能挑战
                if (dp[j - 1] > curDamage) {
                    long newEnergy = dp[j - 1] - curDamage + curReward;
                    dp[j] = Math.max(dp[j], newEnergy);
                }
            }
        }
        // 从最大关卡数开始向下找，第一个能量不为 -1 的 j，就是我们最多能挑战的关卡数
        for (int j = n; j >= 0; j--) {
            if (dp[j] != -1) {
                return j;
            }
        }
        return 0;
    }
}
