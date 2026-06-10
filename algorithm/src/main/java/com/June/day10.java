package com.June;

/**
 * @author: doom
 * @date: 2026/06/10/09:02
 * @description: 力扣3691.最大子数组总值 Ⅱ
 */
public class day10 {
    public static void main(String[] args) {
        System.out.println(maxTotalValue(new int[]{1, 3, 2}, 2));//4
        System.out.println(maxTotalValue(new int[]{4,2,5,1}, 3)); //12
    }

    /**
     * ST表（稀疏表）+二分
     * @param nums
     * @param k
     * @return
     */
    private static long maxTotalValue(int[] nums, int k) {
        return 0;
    }
}
