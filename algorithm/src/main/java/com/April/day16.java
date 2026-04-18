package com.April;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/04/16/09:02
 * @description: 力扣3488.距离最小相等元素查询
 */
public class day16 {
    public static void main(String[] args) {
        System.out.println(solveQueries(new int[]{1, 3, 1, 4, 1, 3, 2}, new int[]{0, 3, 5}));
    }

    /**
     *
     * @param nums 循环数组
     * @param queries
     * @return
     */
    public static List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int n = nums.length;
        for (List<Integer> p : indices.values()) {
            // 前后各加一个哨兵
            int i0 = p.get(0);
            p.add(0, p.get(p.size() - 1) - n);
            p.add(i0 + n);
        }

        List<Integer> ans = new ArrayList<>(queries.length); // 预分配空间
        for (int i : queries) {
            List<Integer> p = indices.get(nums[i]);
            if (p.size() == 3) {
                ans.add(-1);
            } else {
                int j = Collections.binarySearch(p, i);
                ans.add(Math.min(i - p.get(j - 1), p.get(j + 1) - i));
            }
        }
        return ans;
    }
}
