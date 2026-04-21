package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/12/10:16
 * @description:
 *  力扣289. game of life
 */
public class title3_5 {
    public static void main(String[] args) {
        gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
    }

    /**
     * 生命游戏原地算法实现
     *
     * 算法规则：
     * 1. 活细胞周围活细胞数少于2个，死亡（孤立）
     * 2. 活细胞周围活细胞数为2或3个，继续存活
     * 3. 活细胞周围活细胞数超过3个，死亡（过度拥挤）
     * 4. 死细胞周围活细胞数刚好3个，复活（繁殖）
     *
     * 核心思想：使用位运算实现原地更新
     * - 最低位(bit 0)存储当前状态
     * - 次低位(bit 1)存储下一状态
     * - 最后右移一位完成状态转换
     *
     * @param board 二维网格，1表示活细胞，0表示死细胞
     */
    private static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // 第一次遍历：计算邻居并标记下一状态到次低位
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                int curr = 0;

                // 统计周围8个位置的活细胞数（只检查最低位）
                for (int di = -1; di <= 1; di++){
                    for (int dj = -1; dj <= 1; dj++){
                        if (di == 0 && dj == 0) continue;
                        int ni = i + di, nj = j + dj;
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n){
                            if ((board[ni][nj] & 1) == 1){
                                curr++;
                            }
                        }
                    }
                }

                // 应用生命游戏规则，将下一状态写入次低位
                if (board[i][j] == 1){
                    if (curr == 2 || curr == 3){
                        board[i][j] |= 2;
                    }
                }else {
                    if (curr == 3){
                        board[i][j] |= 2;
                    }
                }
            }
        }

        // 第二次遍历：右移一位，用下一状态覆盖当前状态
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                board[i][j] >>= 1;
            }
        }
    }

}
