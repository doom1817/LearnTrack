package com.interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/12/09:57
 * @description:
 */
public class title3_2 {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }

    ;

    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        // 方向数组:右→下→左→上
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0; // 当前方向索引
        int x = 0, y = 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int step = 0; step < m * n; step++) {
            res.add(matrix[x][y]);
            visited[x][y] = true;

            // 计算下一步位置
            int nx = x + dirs[dir][0];
            int ny = y + dirs[dir][1];

            // 如果下一步越界或已访问,转向
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                dir = (dir + 1) % 4; // 顺时针转向
                nx = x + dirs[dir][0];
                ny = y + dirs[dir][1];
            }

            x = nx;
            y = ny;
        }

        return res;
    }


}
