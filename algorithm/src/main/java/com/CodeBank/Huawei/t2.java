package com.CodeBank.Huawei;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: doom
 * @date: 2026/04/28/10:26
 * @description: 要求在一个m×n 的网格中，从左上角走到右下角，只能向右或向下移动，且只能经过值为 0 的格子（1、2、3、4 为障碍物）。
 * 目标是找到一条可行路径，使得转弯次数最少。如果无法到达终点或输入不合法，输出 -1。
 */
public class t2 {
    public static void main(String[] args) {
        // 测试用例 1：常规走线，至少需要一次转弯
        // 路径可为：右->右->下->下，转弯 1 次
        int[][] grid1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println("Test 1: " + minTurns(3, 3, grid1)); // 预期输出: 1

        // 测试用例 2：无路可走，起点或终点被死死挡住
        int[][] grid2 = {{0, 1}, {1, 0}};
        System.out.println("Test 2: " + minTurns(2, 2, grid2)); // 预期输出: -1

        // 测试用例 3：一条直线到底，无需任何转弯
        int[][] grid3 = {{0, 0, 0, 0}};
        System.out.println("Test 3: " + minTurns(1, 4, grid3)); // 预期输出: 0

        // 测试用例 4：非法输入（行数或列数不合法）
        int[][] grid4 = {};
        System.out.println("Test 4: " + minTurns(0, 0, grid4)); // 预期输出: -1
    }

    /**
     * 计算从左上角到右下角的最小转弯次数
     *
     * @param m    网格的行数
     * @param n    网格的列数
     * @param grid 预布线网格（0表示可走，1,2,3,4表示不可走）
     * @return 最少转弯次数。如果不可达或输入非法，返回 -1
     */
    public static int minTurns(int m, int n, int[][] grid) {
        //1.合法判断
        if (m <= 0 || n <= 0) return -1;
        //起点或者终点是障碍物
        if (grid[0][0] != 0 || grid[m - 1][n - 1] != 0) return -1;
        //2。创建数组
        int[][][] dist = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        //3.BFS初始化
        Queue<int[]> queue = new LinkedList<>();
        // 起点特殊处理：转弯次数为0，方向设为-1（表示无方向/起点）
        queue.offer(new int[]{0, 0, -1, 0});
        dist[0][0][0] = 0;
        dist[0][0][1] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int prevDir = curr[2]; // -1:起点, 0:下, 1:右
            int turns = curr[3];

            if (prevDir != -1 && turns > dist[r][c][prevDir]) {
                continue;
            }
            //4.尝试移动：0代表向下，1代表向右

            int[][] moves = {{1, 0, 0}, {0, 1, 1}};

            for (int[] move : moves) {
                int nr = r + move[0];
                int nc = c + move[1];
                int newDir = move[2];
                // 边界检查
                if (nr >= m || nc >= n) continue;
                // 障碍物检查
                if (grid[nr][nc] != 0) continue;

                // 计算新的转弯次数
                int newTurns = turns;
                // 如果不是起点，且方向发生了改变，则转弯数+1
                if (prevDir != -1 && newDir != prevDir) {
                    newTurns++;
                }

                // 松弛操作：如果找到了更优的路径（转弯更少），则更新并入队
                if (newTurns < dist[nr][nc][newDir]) {
                    dist[nr][nc][newDir] = newTurns;
                    queue.offer(new int[]{nr, nc, newDir, newTurns});
                }
            }
        }
        // 5. 获取结果
        int resDown = dist[m - 1][n - 1][0]; // 最后一步向下到达终点
        int resRight = dist[m - 1][n - 1][1]; // 最后一步向右到达终点

        int minTurns = Math.min(resDown, resRight);

        return minTurns == Integer.MAX_VALUE ? -1 : minTurns;
    }
}
