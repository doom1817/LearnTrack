package com.April;

import java.util.stream.IntStream;

/**
 * @author: doom
 * @date: 2026/04/13/15:19
 * @description:
 *  力扣 1848. 到目标元素的最小距离
 */
public class day13 {
    public static void main(String[] args) {
        System.out.println(getMinDistance(new int[]{1,2,3,4,5}, 5,3));
    }
    public static int getMinDistance(int[] nums, int target, int start) {
        return IntStream.range(0, nums.length)
                .filter(i -> nums[i] == target)
                .map(i -> Math.abs(i - start))
                .min()
                .getAsInt();
    }
 }
