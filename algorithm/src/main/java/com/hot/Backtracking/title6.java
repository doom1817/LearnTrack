package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/15/09:45
 * @description:
 *  力扣79. 单词搜索
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
    }
    public static boolean exist(char[][] board, String word) {
        int m = board.length; // 行数
        int n = board[0].length; // 列数
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][]  visited) {

        int m = board.length;
        int n = board[0].length;
        // 边界条件:越界、已访问、字符不匹配 → 失败
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] != word.charAt(index)){
            return false;
        }
        // 如果这是最后一个字符，且匹配成功 → 成功
        if (index == word.length()-1){
            return true;
        }
        visited[i][j] = true; // 标记
        for (int[] dir : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}){
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (dfs(board, nextI, nextJ, word, index + 1, visited)){
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}
