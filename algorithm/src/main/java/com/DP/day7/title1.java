package com.DP.day7;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/05/10:10
 * @Description:
 * 力扣1218：最长的子数组
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(longestSubsequence(new int[]{1,2,3,4},1));
    }

    public static int longestSubsequence(int[] arr, int difference) {
        // 创建一个HashMap，用于存储以某个数值结尾的最长等差子序列长度
        Map<Integer, Integer> dp = new HashMap<>();
        // 初始化最长等差子序列长度为0
        int maxLen = 0;
        /**
         * 遍历数组，对于每个数值num，计算以num为结尾的最长等差子序列长度。
         * 获取以num-difference为结尾的最长等差子序列长度，并加上1，得到以num为结尾的最长等差子序列长度。
         * 将当前 num 对应的最长子序列长度存入哈希表
         * 更新最长等差子序列长度。
         */
        for (int num : arr) {
            int len = dp.getOrDefault(num - difference, 0) + 1;
            dp.put(num, len);
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
