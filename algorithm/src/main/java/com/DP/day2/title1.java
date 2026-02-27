package com.DP.day2;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/17/20:45
 * @Description:
 *  力扣64 最小路径和
 *  给定一个包含非负整数的 m x n 网格 grid，请找出一条从左上角到右下角的路径，
 *  使得路径上数字总和最小。每次只能向下或者向右移动一步。
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); // 应该输出 7
    }
    
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        //初始化第一列
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        //初始化第一行
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j-1];
        }

        //状态转移方程 -> 每次走一步 最小的一步
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[m-1][n-1];
    }
}
