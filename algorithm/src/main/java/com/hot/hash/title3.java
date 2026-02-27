package com.hot.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/19/16:30
 * @Description:
 * 力扣128. 最长连续序列
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(longestConsecutive(new int[]{1,0,1,2}));
    }
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums){
            numSet.add(num);
        }
        int longestStreak = 0;
        for (int num : numSet){
            //只有当 num-1 不存在时，num 才可能是某个连续序列的起点
            if (!numSet.contains(num - 1)){
                int currentNum = num;
                int currentStreak = 1;
                while (numSet.contains(currentNum + 1)){
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
