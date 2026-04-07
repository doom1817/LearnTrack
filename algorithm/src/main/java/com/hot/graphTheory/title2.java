package com.hot.graphTheory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: doom
 * @date: 2026/03/10/21:02
 * @description:
 * 力扣994. 腐烂的橘子
 */
public class title2 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println(orangesRotting(grid));
    }
    /**
     * 计算所有橘子腐烂所需的最少分钟数
     *
     * @param grid m x n 的网格，其中 0 表示空格，1 表示新鲜橘子，2 表示腐烂橘子
     * @return 所有橘子腐烂所需的最少分钟数；如果无法使所有橘子腐烂，返回 -1
     */
    private static int orangesRotting(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        Queue<int[]> queue=new LinkedList<>();
        int fresh=0;

        // 初始化：统计新鲜橘子数量并将所有初始腐烂橘子入队（多源 BFS）
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2)
                    queue.offer(new int[]{i,j});
                if(grid[i][j]==1)
                    fresh++;
            }
        }

        // 边界情况：如果没有新鲜橘子，直接返回 0
        if(fresh==0)return 0;
        int minute=0;
        int [][] dirs={
                {1,0},{-1,0},{0,1},{0,-1}
        };

        // BFS 分层遍历：每一层代表一分钟的腐烂传播过程
        while (!queue.isEmpty()){
            int size=queue.size();
            boolean infected=false;

            // 处理当前分钟的所有腐烂橘子
            for(int i=0;i<size;i++){
                int[] cur=queue.poll();
                int x=cur[0];
                int y=cur[1];

                // 向四个方向传播腐烂
                for(int[] d:dirs){
                    int nx=x+d[0];
                    int ny=y+d[1];
                    if(nx>=0&&nx<m&&ny>=0&&ny<n&&grid[nx][ny]==1){
                        grid[nx][ny]=2;
                        fresh--;
                        queue.offer(new int[]{nx,ny});
                        infected=true;
                    }
                }
            }

            // 只有当本轮有橘子被感染时，时间才增加
            if(infected)minute++;
        }

        // 检查是否还有剩余的新鲜橘子
        return fresh==0?minute:-1;
    }
}
