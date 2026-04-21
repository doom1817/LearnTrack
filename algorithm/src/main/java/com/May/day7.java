package com.May;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/07/09:09
 * @description:
 *  力扣3660. 跳跃游戏IX
 */
public class day7 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxJump(new int[]{2, 1, 3})));
    }

    /**
     * 核心解题思路：前缀最大值 + 后缀最小值（两次线性遍历）
     *
     * 题目规则转化：
     * 1. 向左跳 (j < i)：只能找更大的值 -> 意味着当前位置能触及的“天花板”是其左侧（含自身）的最大值。
     * 2. 向右跳 (j > i)：只能找更小的值 -> 意味着如果右边有更小的值，我们就可以跳过去，进而可能到达更大的区域。
     *
     * 算法步骤：
     * 第一步（正向遍历）：计算每个位置的“前缀最大值”。这代表了如果不考虑向右跳，仅凭向左跳能拿到的最大值。
     * 第二步（逆向遍历）：维护一个“后缀最小值”。
     *    - 如果当前左侧的最大值(preMax) > 右侧出现过的最小值(sufMin)，
     *    - 说明我们可以利用“先向右跳到那个更小的值，再向左跳”的策略，去触达更前方的高地。
     *    - 此时，当前位置的答案可以直接继承其右侧位置(i+1)的最终结果。
     *
     * @param nums
     * @return
     */
    private static int[] maxJump(int[] nums){
        if (nums.length==0)return new int[]{0};
        int n = nums.length;
        int[] ans = new int[n];
        //1.正向遍历
        // preMax[i] 存储从下标 0 到 i 之间的最大值
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++){
            preMax[i] = Math.max(preMax[i-1], nums[i]);
        }
        //2.逆向遍历
        // sufMin 用于记录从数组最右端到当前位置 i 之间，所遇到过的最小值
        int sufMin = Integer.MAX_VALUE;
        for (int i = n-1; i >= 0; i--){
            ans[i] = preMax[i];
            if (preMax[i]>sufMin){
                ans[i] = ans[i+1];
            }
            // 更新 sufMin
            sufMin = Math.min(sufMin, nums[i]);
        }
        return ans;
    }
}
