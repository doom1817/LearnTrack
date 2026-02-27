package com.DP.day8;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/06/11:05
 * @Description:
 * 力扣1964.找出到每个位置为止最长的有效障碍赛跑路线
 */
public class title1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{1, 2, 3, 2})));
        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{2,2,1})));
        System.out.println(Arrays.toString(longestObstacleCourseAtEachPosition(new int[]{3,1,5,6,4,2})));
    }

    private static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] res = new int[n];
        List<Integer> tail = new ArrayList<>(); // tail.get(i) 表示长度为 i+1 的非递减子序列的最小结尾值

        for (int i = 0; i < n; i++) {
            int obstacle = obstacles[i];
            int pos = findInsertPosition(tail, obstacle); // 调用二分查找方法
            res[i] = pos + 1; // 当前最长有效路径长度
            updateTail(tail, pos, obstacle); // 调用贪心更新方法
        }

        return res;
    }

    /**
     * 使用二分查找找到插入位置
     */
    private static int findInsertPosition(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 更新 tail 列表，维护贪心策略
     */
    private static void updateTail(List<Integer> tail, int pos, int value) {
        if (pos == tail.size()) {
            tail.add(value); // 如果是新长度，则添加到末尾
        } else {
            tail.set(pos, value); // 否则替换当前位置的值
        }
    }
}
