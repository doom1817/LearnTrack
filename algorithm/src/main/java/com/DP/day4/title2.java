package com.DP.day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/31/22:01
 * @Description:
 * 力扣 139 单词拆分
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

    /**
     * 以下返回false
     * 情况1：如果s中的字母不存在与字典中的单词中，则返回false
     * 情况2：将s的根据字典删除后，如果还存在无法删去的字母，则返回false
     * 情况3：当字典为空时
     *
     * @param s 待拆分的字符串，非空
     * @param wordDict 字符串字典，包含可用的单词列表，非空，无重复
     * @return 如果字符串 s 可以完全由字典中的单词拼接则返回 true，否则返回 false
     */

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set< String> dict = new HashSet<>(wordDict); // 创建一个集合，将wordDict中的单词添加到集合中
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空字符串可以被匹配
        //外层循环：i表示字符串的结束位置 内层循环：j表示字符串的起始位置
        // 状态转移方程：dp[i] = dp[j] && dict.contains(s.substring(j, i))
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    
}
