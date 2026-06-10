package com.June;

import java.util.PriorityQueue;

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
     * 使用ST表（稀疏表）预处理区间最值，结合最大堆贪心选择k个最优子数组
     *
     * 算法思想：
     * 1. 构建ST表支持O(1)查询任意区间的最大值与最小值
     * 2. 初始将整个数组[0, n)加入最大堆，按极差（max-min）排序
     * 3. 每次从堆顶取出极差最大的子数组，累加其极差到答案
     * 4. 将该子数组分裂为两个更小的子数组[l, r-1)和[l+1, r)，重新入堆
     * 5. 重复k次或直到堆顶极差<=0时停止
     *
     * 核心逻辑：通过不断分裂区间并优先选择极差最大的子数组，确保总值最大化
     *
     * @param nums 输入数组
     * @param k 需要选择的子数组数量
     * @return k个子数组的最大总值（各子数组极差之和）
     */
    private static long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        ST st = new ST(nums);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // 最大堆
        pq.add(new int[]{st.query(0, n), 0, n}); // 子数组极差，左端点，右端点加一

        long ans = 0;
        while (k-- > 0 && pq.peek()[0] > 0) {
            int[] top = pq.poll();
            int d = top[0], l = top[1], r = top[2];
            ans += d;
            pq.add(new int[]{st.query(l, r - 1), l, r - 1});
            if (r == n && l + 1 < n) {
                pq.add(new int[]{st.query(l + 1, n), l + 1, n});
            }
        }
        return ans;
    }
}
/**
 * ST表（Sparse Table，稀疏表）数据结构
 *
 * 用于快速查询静态数组任意区间的最小值和最大值
 * 预处理时间复杂度：O(n log n)
 * 单次查询时间复杂度：O(1)
 *
 * 核心思想：利用倍增法预处理所有长度为2^i的区间的最值
 */
class ST {
    private final int[][] stMin;
    private final int[][] stMax;

    /**
     * 构建ST表，预处理数组的所有区间最值
     *
     * @param a 输入数组
     */
    public ST(int[] a) {
        int n = a.length;
        int w = 32 - Integer.numberOfLeadingZeros(n);
        stMin = new int[w][n];
        stMax = new int[w][n];

        for (int j = 0; j < n; j++) {
            stMin[0][j] = a[j];
            stMax[0][j] = a[j];
        }

        // 倍增预处理：计算所有长度为2^i的区间的最值
        for (int i = 1; i < w; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                stMin[i][j] = Math.min(stMin[i - 1][j], stMin[i - 1][j + (1 << (i - 1))]);
                stMax[i][j] = Math.max(stMax[i - 1][j], stMax[i - 1][j + (1 << (i - 1))]);
            }
        }
    }

    /**
     * 查询区间[l, r)的极差（最大值 - 最小值）
     *
     * 利用两个长度为2^k的重叠区间覆盖[l, r)，其中k = floor(log2(r-l))
     *
     * @param l 区间左端点（包含）
     * @param r 区间右端点（不包含）
     * @return 区间内最大值与最小值的差
     */
    public int query(int l, int r) {
        int k = 31 - Integer.numberOfLeadingZeros(r - l);
        int mn = Math.min(stMin[k][l], stMin[k][r - (1 << k)]);
        int mx = Math.max(stMax[k][l], stMax[k][r - (1 << k)]);
        return mx - mn;
    }
}