package com.hot.slidingWindow;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/22/15:36
 * @Description:
 * 力扣3. 无重复字符的最长子串
 */
public class title1 {
    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));

    }
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        }
        int[] charIndexMap = new int[128];// 窗口
        int left = 0, right; // 滑动窗口的左右边界
        int maxlen = 0; // 最长子串的长度

        for (right = 0; right < len; right++){
            char cur = s.charAt(right); // 当前字符
            if (charIndexMap[cur] > 0){ //如果当前字符在窗口中已经出现过，则更新窗口的左边界为当前字符的右边界
                left = Math.max(left, charIndexMap[cur]);
            }
            charIndexMap[cur] = right + 1;
            maxlen = Math.max(maxlen, right - left + 1);
        }
        return maxlen;
    }
}
