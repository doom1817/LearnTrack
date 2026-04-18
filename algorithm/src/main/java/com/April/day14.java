package com.April;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/04/14/15:09
 * @description:
 *  力扣2463. 最小移动总距离
 *  排序/动态规划/单调队列
 */
public class day14 {
    public static void main(String[] args) {
        System.out.println(minimumTotalDistance(Arrays.asList(0,4,6), new int[][]{ {2, 2}, {6,2}}));//4
    }
    public  static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // 1. 排序
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);
        int n = robot.size();
        int m = factory.length;

        // 将 robot 转为数组方便访问
        int[] rob = new int[n];
        for (int i = 0; i < n; i++) rob[i] = robot.get(i);

        // 2. DP 滚动数组，prev[i] = 前 i 个机器人用前 j-1 个工厂的最小距离
        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1];
        Arrays.fill(prev, INF);
        prev[0] = 0; // 0 个机器人距离为 0

        // 3. 逐个工厂处理
        for (int j = 0; j < m; j++) {
            int pos = factory[j][0]; // 当前工厂的位置
            int cap = factory[j][1]; // 当前工厂的容量

            // 计算当前工厂的绝对值前缀和
            long[] absPref = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                absPref[i] = absPref[i - 1] + Math.abs(rob[i - 1] - pos);
            }

            // 当前工厂的 dp 结果
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            cur[0] = 0; // 0 个机器人总是可行

            // 单调队列，存放索引 t，按 (prev[t] - absPref[t]) 递增
            Deque<Integer> dq = new ArrayDeque<>();

            // 遍历机器人数量 i
            for (int i = 0; i <= n; i++) {
                // 将当前索引 i 加入队列（前提是 prev[i] 可达）
                if (prev[i] < INF) {
                    long val = prev[i] - absPref[i];
                    // 维护队列单调递增（尾部移除更大值）
                    while (!dq.isEmpty() && (prev[dq.peekLast()] - absPref[dq.peekLast()]) >= val) {
                        dq.pollLast();
                    }
                    dq.offerLast(i);
                }

                // 移除超出窗口左边界（t < i - cap）的索引
                int leftBound = i - cap;
                while (!dq.isEmpty() && dq.peekFirst() < leftBound) {
                    dq.pollFirst();
                }

                // 如果队列非空，则用最小值更新 cur[i]
                if (!dq.isEmpty()) {
                    int best = dq.peekFirst();
                    cur[i] = (prev[best] - absPref[best]) + absPref[i];
                }
            }

            // 滚动数组
            prev = cur;
        }

        return prev[n];
    }
}
