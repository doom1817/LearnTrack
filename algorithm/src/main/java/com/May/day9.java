package com.May;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/09/09:04
 * @description:
 *  力扣1914. 循环轮转矩阵
 */
public class day9 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(rotateGrid(new int[][]{{40,10},{30,20}}, 1))); //[[10,20],[40,30]]
        System.out.println(Arrays.deepToString(rotateGrid(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}},2)));//[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
    }

    /**
     *  模拟
     *  移动k次，每一次都是每一圈的逆时针运动一次
     *  思考：需不要要将每一圈单独出来为一层数组，每次移动就是内部的移动，运动完再重新组合？
     *   情况1.有一层移动结束时和开始一样->如何分辨：倍数关系？
     * @param grid
     * @param k
     * @return
     */
    public static int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length; // 行
        int n = grid[0].length; // 列
        for (int layer = 0; layer < Math.min(m, n) / 2; layer++){
            List<Integer> list = new ArrayList<>();
            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;
            //逆时针添加
            //1. 左上->左下  (不包含左下角)
            for (int i = top; i < bottom; i++){
                list.add(grid[i][left]);
            }
            //2. 左下->右下 (不包含右下角)
            for (int i = left; i < right; i++){
                list.add(grid[bottom][i]);
            }
            //3. 右下->右上 (不包含右上角)
            for (int i = bottom; i > top; i--){
                list.add(grid[i][right]);
            }
            //4. 右上->左上 (不包含左上角)
            for (int i = right; i > left; i--){
                list.add(grid[top][i]);
            }
            /*
             *  这里的size 其实就是每一圈的周长
             *  H = (m - 1 - layer) - layer + 1
             *  W = (n - 1 - layer) - layer + 1
             *  所以周长 = 2*(H + W) - 4 = list.size()
             */
            // 计算长度
            int len = list.size();
            if (k % len == 0) continue;
            int realK = k % len;
            if (realK == 0) continue; // 如果等于0，则不需要旋转
            // 逆时针旋转
            int idx = 0; // 旋转的索引
            // 左上->左下
            for (int i = top; i < bottom; i++){
                grid[i][left] = list.get((idx + len - realK) % len);
                idx++;
            }
            // 左下->右下
            for (int i = left; i < right; i++){
                grid[bottom][i] = list.get((idx + len - realK) % len);
                idx++;
            }
            // 右下->右上
            for (int i = bottom; i > top; i--){
                grid[i][right] = list.get((idx + len - realK) % len);
                idx++;
            }
            // 右上->左上
            for (int i = right; i > left; i--){
                grid[top][i] = list.get((idx + len - realK) % len);
                idx++;
            }
        }
        return grid;
    }
}
