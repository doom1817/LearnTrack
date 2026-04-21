package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/08/09:56
 * @description:
 *  力扣3. 无重复字符的最长子串
 */
public class title2_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
    private static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len<2) return len;
        int[] charIndexMap = new int[128];// 映射ASCII码->就是将字符映射成ASCII码
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < len; right++){
            char curr = s.charAt(right);
            if (charIndexMap[curr]>0){// 存在则跳过
                left = Math.max(charIndexMap[curr],left);
            }
            // 更新最大长度
            maxLen = Math.max(maxLen,right-left+1);
            charIndexMap[curr] = right+1;// 更新当前字符的索引
        }
        return maxLen;
    }
}
