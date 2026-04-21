package com.CodeBank.week501;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/05/10/18:22
 * @description:
 */
public class q2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countWordOccurrences(new String[]{"hello wor","ld hello"}, new String[]{"hello","world","wor"})));// [2, 1, 0]
        System.out.println(Arrays.toString(countWordOccurrences(new String[]{"a--b a-","-c"}, new String[]{"a","b","c"})));// [2,1,1]
        System.out.println(Arrays.toString(countWordOccurrences(new String[]{"hello"}, new String[]{"hello","ell"})));// [1,0]
    }
    private static int[] countWordOccurrences(String[] chunks, String[] queries) {
        // 1. 拼接所有字符串
        StringBuilder sb = new StringBuilder();
        for (String chunk : chunks) {
            sb.append(chunk);
        }
        String s = sb.toString();

        // 2. 解析有效单词并统计频率
        Map<String, Integer> wordCount = new HashMap<>();
        int n = s.length();
        StringBuilder cur = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                // 小写字母：直接加入当前单词
                cur.append(c);
            } else if (c == '-') {
                // 连字符：只有当前单词非空且下一个字符是小写字母时才是合法的
                if (cur.length() > 0 && i + 1 < n && s.charAt(i + 1) >= 'a' && s.charAt(i + 1) <= 'z') {
                    cur.append(c); // 合法连字符，加入当前单词
                } else {
                    // 非法连字符，作为分隔符：结束当前单词
                    if (cur.length() > 0) {
                        String word = cur.toString();
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                        cur.setLength(0);
                    }
                    // 连字符本身被忽略，不进入任何单词
                }
            } else {
                // 其他字符（空格、标点等）作为分隔符：结束当前单词
                if (cur.length() > 0) {
                    String word = cur.toString();
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    cur.setLength(0);
                }
            }
        }

        // 处理最后一个单词
        if (cur.length() > 0) {
            String word = cur.toString();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 3. 生成查询结果
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = wordCount.getOrDefault(queries[i], 0);
        }
        return ans;
    }
}
