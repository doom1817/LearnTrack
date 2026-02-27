package com.hot.twoPointer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/20:24
 * @Description:
 * 力扣28. 找出字符串中第一个匹配项的下标
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(strStr(new String("sadbutsad"), new String("sad")));
        System.out.println(strStr(new String("aabba"), new String("bba")));
    }

    /**
     *  解法：暴力匹配
     *  思路：遍历haystack，对每个字符进行匹配
     *  匹配成功则返回索引，匹配失败则返回-1
     *  通过条件来排除匹配失败的情况 ->1. haystack长度小于needle长度
     *                          -> 2. haystack长度小于needle长度+i
     *                          -> 3. haystack长度小于needle长度+i+j
     *  匹配成功则返回索引
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        for (int i = 0; i < haystack.length(); i++){
            for (int j = 0; j < needle.length(); j++){
                if (i+j >= haystack.length() || haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
                // 判断是否匹配
                if (j == needle.length()-1) {
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * 解法：KMP算法
     * 思路：
     * 1. 创建next数组，记录needle中每个字符的next值
     * 2. 创建i和j两个指针，i指向haystack的索引，j指向needle的索引
     * 3. 遍历haystack，对每个字符进行匹配
     * 4. 匹配成功则j++，i++
     * 5. 匹配失败则j = next[j-1]
     * 6. 匹配成功则返回i-j
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr2(String haystack, String needle) {
        // 输入校验：处理边界情况
        if (needle.isEmpty()) return 0;
        if (haystack.length() < needle.length()) return -1;

        // 构建next数组
        int[] next = buildNext(needle);

        // KMP匹配过程
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j; // 匹配成功，返回起始位置
                }
            } else {
                if (j > 0) {
                    j = next[j - 1]; // 失败时回退到next位置
                } else {
                    i++; // 第一个字符就不匹配，移动主串指针
                }
            }
        }
        return -1; // 未找到匹配
    }

    /**
     * 构建KMP算法中的next数组
     * @param needle 模式串
     * @return next数组
     */
    private static int[] buildNext(String needle) {
        int[] next = new int[needle.length()];
        int prefixLen = 0; // 当前最长相等前后缀长度
        int i = 1;

        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(prefixLen)) {
                prefixLen++;
                next[i] = prefixLen;
                i++;
            } else {
                if (prefixLen != 0) {
                    prefixLen = next[prefixLen - 1]; // 回退到上一个可能的位置
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }
}
