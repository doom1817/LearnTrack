package com.June;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * @author: doom
 * @date: 2026/06/09/14:08
 * @description:
 *  力扣3689. 最大子数组总值Ⅰ
 */
public class day9 {
    public static void main(String[] args) {
        System.out.println(maxTotalValue(new int[]{1,3,2}, 2));//4
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    private static long  maxTotalValue(int[] nums, int k) {
        IntSummaryStatistics stats = Arrays.stream(nums).summaryStatistics();
        int max = stats.getMax();
        int min = stats.getMin();
        return (long)k*(max-min);
    }
}
