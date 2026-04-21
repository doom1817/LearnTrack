package com.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/15/10:20
 * @description:
 *   力扣57.插入区间
 */
public class title4_2 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(insert(new int[][]{{1, 3}, {6,9}}, new int[]{2, 5})));
    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        // 阶段一：将所有在新区间左侧且不重叠的区间加入结果
        while (i < n && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        // 阶段二：将所有与新区间重叠的区间进行合并
        while (i < n && intervals[i][0] <= newInterval[1]){
            // 合并
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);
        // 阶段三：将所有在新区间右侧且不重叠的区间加入结果
        while (i < n){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
