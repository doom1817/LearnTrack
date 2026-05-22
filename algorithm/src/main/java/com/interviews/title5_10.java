package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/21/10:28
 * @description:
 *  力扣28. 找出字符串中第一个匹配项的下标
 */
public class title5_10 {
    public static void main(String[] args) {
        System.out.println(strStr1("sadbutsad", "sad")); //0
        System.out.println(strStr2("sadbutsad", "sad")); //0
    }
    //最快方法就是调用 haystack.indexOf(needle);直接返回
    //解法1. 暴力解法-> 滑动窗口
    private static int strStr1(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n-m; i++){
            int j = 0;
            while (j < m && haystack.charAt(i+j) == needle.charAt(j))j++;
            if (j == m) return i;
        }
        return -1;
    }
    //解法2. KMP算法
    private static int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int n = haystack.length(), m = needle.length();

        // 构建next数组（前缀表），用于记录模式串的最长相等前后缀长度
        int[] next = new int[m];

        // 构造next数组：i指向后缀末尾，j指向前缀末尾（也表示当前已匹配的长度）
        for (int i = 1, j = 0; i < m; i++) {
            // 当前后字符不匹配时，根据next数组回退j到上一个可匹配位置
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            // 当前后字符匹配时，j向后移动一位
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 记录当前位置的最长相等前后缀长度
            next[i] = j;
        }

        // KMP匹配过程：i遍历主串，j遍历模式串
        for (int i = 0, j = 0; i < n; i++) {
            // 当字符不匹配时，根据next数组调整j的位置，避免回溯i
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            // 当字符匹配时，j向后移动一位
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 当j等于模式串长度m时，说明完全匹配，返回匹配起始位置
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
