package com.interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/05/08/10:02
 * @description: 力扣30. 串联所有单词的子串
 */
public class title2_4 {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));//[0,9]
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));//[8]
    }

    private static List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length(); // 一个单词的长度
        int windowLen = wordLen * words.length; // 所有单词的总长度，即窗口大小

        // 目标：窗口中的单词出现次数必须与 targetCnt 完全一致
        Map<String, Integer> targetCnt = new HashMap<>();
        for (String w : words) {
            targetCnt.merge(w, 1, Integer::sum); // targetCnt[w]++
        }

        List<Integer> ans = new ArrayList<>();
        // 枚举第一个窗口的左端点，做 wordLen 次起点不同的滑动窗口
        for (int start = 0; start < wordLen; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            int overload = 0; // 统计过多的单词个数（包括不在 words 中的单词）
            // 枚举窗口最后一个单词的右开端点
            for (int right = start + wordLen; right <= s.length(); right += wordLen) {
                // 1. inWord 进入窗口
                String inWord = s.substring(right - wordLen, right);
                // 下面 cnt[inWord]++ 后，inWord 的出现次数过多
                if (cnt.getOrDefault(inWord, 0).equals(targetCnt.getOrDefault(inWord, 0))) {
                    overload++;
                }
                cnt.merge(inWord, 1, Integer::sum); // cnt[inWord]++

                int left = right - windowLen; // 窗口第一个单词的左端点
                if (left < 0) { // 窗口大小不足 windowLen
                    continue;
                }

                // 2. 更新答案
                // 如果没有超出 targetCnt 的单词，那么也不会有少于 targetCnt 的单词
                if (overload == 0) {
                    ans.add(left);
                }

                // 3. 窗口最左边的单词 outWord 离开窗口，为下一轮循环做准备
                String outWord = s.substring(left, left + wordLen);
                cnt.merge(outWord, -1, Integer::sum); // cnt[outWord]--
                if (cnt.get(outWord).equals(targetCnt.getOrDefault(outWord, 0))) {
                    overload--;
                }
            }
        }

        return ans;
    }
}
