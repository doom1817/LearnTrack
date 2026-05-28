package com.interviews;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/05/27/09:38
 * @description:
 *  力扣 128. 最长连续序列
 */
public class title8_1 {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));// 4
    }
    private static int longestConsecutive(int[] nums)  {
        Set< Integer> numSet = new HashSet<>();
        for (int num : nums){
            numSet.add(num);
        }
        int longestStreak = 0;
        for (int num : numSet){
            if (!numSet.contains(num - 1)){//只有当 num-1 不存在时，num 才可能是某个连续序列的起点
                int currentNum = num; //记录当前连续序列中正在检查的数字
                int currentStreak = 1; //记录从起点到 currentNum 为止，已经连续了多少个数字（序列的长度）
                while (numSet.contains(currentNum + 1)){ //找到当前连续序列的终点
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
