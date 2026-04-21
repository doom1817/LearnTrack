package com.interviews;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/05/12/09:35
 * @description: 力扣 36. 有效的数独
 */
public class title3_1 {
    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        })); //输出：true
    }

    private static boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;

                // 生成三个约束字符串
                String rowKey = c + "_in_row_" + i;
                String colKey = c + "_in_col_" + j;
                String boxKey = c + "_in_box_" + (i / 3) + "_" + (j / 3);

                // 尝试添加，若有任何一个已存在，则无效
                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }
        return true;
    }
}
