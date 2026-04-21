package com.May;

import java.util.*;

/**
 * 力扣2770. 达到末尾下标所需的最大跳跃次数
 * 优化：离散化 + 线段树，O(n^2) -> O(n log n)
 */
public class day10 {
    public static void main(String[] args) {
        System.out.println(maximumJumps(new int[]{1,3,6,4,1,2}, 2)); // 3
        System.out.println(maximumJumps(new int[]{0,2,1,3}, 1));     // 2 (路径: 0->1->2->3)
        System.out.println(maximumJumps(new int[]{0,1,2,3}, 1));     // 3 (路径: 0->1->2->3)
        System.out.println(maximumJumps(new int[]{1,2,3,4}, 0));     // -1 (无法跳跃，因为差值至少为1)
        System.out.println(maximumJumps(new int[]{1,2,3,4}, 1));     // 3
    }

    private static int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 离散化：值域压缩到 [0, m-1]
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        List<Integer> unique = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i-1]) {
                unique.add(sorted[i]);
            }
        }
        int m = unique.size();

        // 线段树维护每个值对应的最大 dp
        SegmentTree seg = new SegmentTree(m);
        seg.update(Collections.binarySearch(unique, nums[0]), 0);

        for (int j = 1; j < n; j++) {
            int val = nums[j];
            long left = (long) val - target;
            long right = (long) val + target;

            int l = lowerBound(unique, left);
            int r = upperBound(unique, right) - 1;

            if (l <= r && l < m && r >= 0) {
                int maxDp = seg.query(l, r);
                if (maxDp != -1) {
                    dp[j] = maxDp + 1;
                }
            }

            if (dp[j] != -1) {
                seg.update(Collections.binarySearch(unique, val), dp[j]);
            }
        }
        return dp[n - 1];
    }

    private static int lowerBound(List<Integer> arr, long target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static int upperBound(List<Integer> arr, long target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    // 线段树：维护区间最大值
    private static class SegmentTree {
        private int[] tree;
        private int n;

        public SegmentTree(int size) {
            this.n = size;
            this.tree = new int[4 * size];
            Arrays.fill(tree, -1);
        }

        public void update(int pos, int val) {
            update(0, 0, n - 1, pos, val);
        }

        private void update(int node, int start, int end, int pos, int val) {
            if (start == end) {
                tree[node] = Math.max(tree[node], val);
                return;
            }
            int mid = (start + end) >>> 1;
            if (pos <= mid) {
                update(2 * node + 1, start, mid, pos, val);
            } else {
                update(2 * node + 2, mid + 1, end, pos, val);
            }
            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public int query(int l, int r) {
            return query(0, 0, n - 1, l, r);
        }

        private int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return -1;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) >>> 1;
            int leftMax = query(2 * node + 1, start, mid, l, r);
            int rightMax = query(2 * node + 2, mid + 1, end, l, r);
            return Math.max(leftMax, rightMax);
        }
    }
}
