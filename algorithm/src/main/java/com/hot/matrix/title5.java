package com.hot.matrix;

/**
 * @author: doom
 * @date: 2026/03/02/23:11
 * @description:
 *  力扣807.保持城市天际线
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(maxIncreaseKeepingSkyline(new int[][]{{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}}));
        System.out.println(maxIncreaseKeepingSkyline(new int[][]{{0,0,0},{0,0,0},{0,0,0}}));
    }
    //每栋楼都可以长高到"不超过它所在行和列的最高限制
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] rowMax = new int[n];
        int[] colMax = new int[n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                rowMax[i] = Math.max(rowMax[i], grid[i][j]); //行最大值
                colMax[j] = Math.max(colMax[j], grid[i][j]); //列最大值
            }
        }

        int totalIncrease = 0; //总增加量
        //遍历矩阵，计算每栋楼增加的高度
       for (int i = 0; i < n; i++){
           for (int j = 0; j < n; j++){
               //当前位置的最小高度
               totalIncrease += Math.min(rowMax[i], colMax[j]) - grid[i][j]; //增加量
           }
       }
       return totalIncrease;
    }
}
