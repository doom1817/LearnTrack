package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/15/10:31
 * @description:
 *  力扣51. N皇后
 */
public class title8 {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0)return res;

        //初始化棋盘
        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n - 1];
        boolean[] antiDiag = new boolean[2 * n - 1];
        backtrack(res, new ArrayList<>(), n, 0, cols, mainDiag, antiDiag);

        return res;
    }
    private static void backtrack(List<List<String>> res, List<String> path, int n, int row,
                                  boolean[] cols, boolean[] mainDiag, boolean[] antiDiag) {
        // 1. 递归终止条件：所有行都已成功放置皇后
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 2. 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 3. 计算对角线索引
            int mainDiagIndex = row - col + n - 1;
            int antiDiagIndex = row + col;

            // 4. 检查是否与已有皇后冲突
            if (cols[col] || mainDiag[mainDiagIndex] || antiDiag[antiDiagIndex]) {
                continue;
            }

            // 5. 标记当前选择
            cols[col] = true;
            mainDiag[mainDiagIndex] = true;
            antiDiag[antiDiagIndex] = true;

            // 6. 构建当前行的棋盘表示
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(i == col ? 'Q' : '.');
            }
            path.add(sb.toString());

            // 7. 递归处理下一行
            backtrack(res, path, n, row + 1, cols, mainDiag, antiDiag);

            // 8. 回溯：取消标记并移除路径
            cols[col] = false;
            mainDiag[mainDiagIndex] = false;
            antiDiag[antiDiagIndex] = false;
            path.remove(path.size() - 1);
        }
    }
}
