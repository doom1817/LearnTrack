package com.June;

/**
 * @author: doom
 * @date: 2026/06/17/09:06
 * @description: 力扣3614. 用特殊操作处理字符串Ⅱ
 */
public class day17 {
    public static void main(String[] args) {
        System.out.println(processStr("a#b%*", 1));// a
        System.out.println(processStr("cd%#*#", 3));// d
    }

    private static final long MAX_LEN = (long) 1e15 + 1;

    private static char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n];

        //1.正向扫描，记录每步操作后的长度
        long curlen = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                curlen++;
            } else if (c == '*') {
                if (curlen > 0) curlen--;
            } else if (c == '#') {
                curlen = Math.min(curlen * 2, MAX_LEN);
            }
            len[i] = curlen;
        }
        //2.检查k是否越界
        if (k >= curlen) return '.';
        //3.反向扫描，找到第k个字符
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (k == len[i] - 1) {
                    return c;
                }
            } else if (c == '#') {
                long halfLen = (i > 0) ? len[i - 1] : 0;
                if (halfLen > 0) {
                    k = k % halfLen;
                }
            } else if (c == '%') {
                k = len[i] - k - 1;

            }
        }
        return '.';
    }
}