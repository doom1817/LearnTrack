package com.DP.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/05/11:06
 * @Description:
 * 力扣 354 俄罗斯套娃信封问题
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }
    public static int maxEnvelopes(int[][] envelopes) {
        // 双关键字排序：宽度升序，高度降序
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 300. 最长递增子序列
        List<Integer> g = new ArrayList<>();
        for (int[] e : envelopes) {
            int h = e[1];
            int j = lowerBound(g, h);
            if (j < g.size()) {
                g.set(j, h);
            } else {
                g.add(h);
            }
        }
        return g.size();
    }

    private static int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (g.get(mid) >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right; // 或者 left+1
    }
}
