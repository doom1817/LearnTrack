package com.hot.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/02/24/16:18
 * @description:
 * 力扣56. 合并区间
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
    }
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 按区间的起始位置排序
        List<int[]> merges = new ArrayList<>();
        merges.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++){
            int[] cur = intervals[i];
            int[] last = merges.get(merges.size()-1); // 获取最后一个区间
            if (cur[0] <= last[1]){ // 判断当前区间是否与最后一个区间有重叠
                last[1] = Math.max(last[1], cur[1]);
            }
            else{
                merges.add(cur);
            }
        }
        return merges.toArray(new int[merges.size()][]);
    }
}
