package com.May;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/12/09:06
 * @description:
 *  力扣1665. 完成所有任务的最少初始能量
 */
public class day12 {
    public static void main(String[] args) {
        System.out.println(minimumEffort(new int[][]{{1,2},{2,4},{4,8}}));//输出：8
        System.out.println(minimumEffort(new int[][]{{1,3},{2,4},{10,11},{10,12},{8,9}}));//输出：32
        System.out.println(minimumEffort(new int[][]{{1,7},{2,8},{3,9},{4,10},{5,11},{6,12}})); //输出：27
    }
    private static int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks,(a,b)->(b[0] - b[1])-(a[0] - a[1])); // 按 (minimum - actual) 降序排序
        int ans = 0; //能量
        for (int[] task : tasks){
            int actual= task[0]; // 消耗
            int minimum = task[1]; // 要求

            ans = Math.max(ans+actual, minimum);
        }
        return ans;
    }
}
