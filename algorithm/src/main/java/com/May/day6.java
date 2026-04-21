package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/06/09:00
 * @description:
 *  力扣1861.旋转盒子
 */
public class day6 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(rotateTheBox(new char[][]{{'#', '.', '#'}})));
    }
    /**
     * 模拟
     * @param boxGrid
     * @return
     */
    private static char[][] rotateTheBox(char[][] boxGrid) {
        // 边界检查：防止空指针和空数组异常
        if (boxGrid == null || boxGrid.length == 0 || boxGrid[0].length == 0) {
            return new char[0][0];
        }
        // 行数列数
        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] ans = new char[n][m];
        // 初始化结果矩阵，所有位置填充为空位
        for (int i = 0; i < n; i++){
            Arrays.fill(ans[i], '.');
        }
        // 双指针处理：遍历原矩阵每一行，从右向左扫描，模拟重力下落
        for (int i = 0; i < m; i++) {
            int nextAvailableRow = n - 1;
            int rotatedCol = m - 1 - i;

            for (int j = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '#') {
                    // 遇到石头：放置到可放置位置并下移指针
                    ans[nextAvailableRow][rotatedCol] = '#';
                    nextAvailableRow--;
                } else if (boxGrid[i][j] == '*') {
                    // 遇到障碍物：固定位置并重置可放置区域到障碍物上方
                    ans[j][rotatedCol] = '*';
                    nextAvailableRow = j - 1;
                }
                // 空格子('.')无需处理，保持初始化值
            }
        }
        return ans;
    }
}
