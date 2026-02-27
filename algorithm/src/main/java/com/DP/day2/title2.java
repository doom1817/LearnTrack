package com.DP.day2;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/17/21:20
 * @Description:
 * 力扣 63. 不同路径 II
 * 在m×n网格中，机器人从左上角移动到右下角，每次只能向下或向右移动一步
 * */

public class title2 {
    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }
    /**
     * 使用动态规划计算有障碍物网格中的不同路径数量
     * @param obstacleGrid 网格数组，1表示障碍物，0表示空位
     * @return 从左上角到右下角的不同路径数量
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length; // 网格的行数
        int n = obstacleGrid[0].length; // 网格的列数
        //初始判断,如果开始就是障碍物，则直接返回0
        if (obstacleGrid[0][0] == 1) return 0;
//        初始化起点为1
        obstacleGrid[0][0] = 1;
        //初始化第一列
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                // 当前格子是障碍物，路径数量为0
                obstacleGrid[i][0] = 0;
            } else {
                //// 当前格子不是障碍，但前一个格子已经是0（被障碍阻挡），当前也应该是0
                obstacleGrid[i][0] = obstacleGrid[i-1][0];

            }
        }
        //初始化第一行
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                // 当前是障碍物，路径数为0
                obstacleGrid[0][j] = 0;
            } else {
                obstacleGrid[0][j] = obstacleGrid[0][j-1];
            }
        }
        //状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    // 当前是障碍物，路径数为0[2,7]
                    obstacleGrid[i][j] = 0;
                } else {
                    // 状态转移方程：路径数 = 上方路径数 + 左方路径数[1,4]
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}
