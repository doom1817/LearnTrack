package com.interviews;


import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/18/22:27
 * @description:
 *  力扣452. 用最少数量的箭引爆气球
 */
public class title4_3 {
    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));// 2
    }
    private static  int findMinArrowShots(int[][] points) {
        if (points.length == 0){
            return 0;
        }
        Arrays.sort(points,(a,b)->Integer.compare(a[0],b[0]));
        int arrows =1;// 初始化 至少需要一支箭
        int current = points[0][1];

        for (int i = 1; i < points.length; i++){
            // 如果当前气球的左边界大于当前箭的右边界，则需要一支新的箭
            if (points[i][0] > current){
                arrows++;
                current = points[i][1]; // 更新当前箭的坐标
            }
            else {
                current = Math.min(current,points[i][1]);
            }
        }
        return arrows;
    }
}
