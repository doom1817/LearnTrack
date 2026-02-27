package com.hot.Substring;

/**
 * @author: doom
 * @date: 2026/02/23/20:34
 * @description:
 * 力扣76. 最小覆盖子串
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("a", "aa"));
    }
    /**
     * 滑动窗口
     * 算法思路：使用滑动窗口（Two Pointers） + 前缀计数
     * 1. 统计目标字符串t中每个字符的出现次数（tCount）
     * 2. 使用双指针left和right维护滑动窗口
     * 3. 用windowCounts统计当前窗口中各字符的出现次数
     * 4. 用formed记录当前窗口中已满足要求的字符种类数（当windowCounts[c] == tCount[c]时）
     * 5. 当formed == required（t的长度）时，窗口满足条件，尝试收缩左边界
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (t.isEmpty() || s.isEmpty())return  "";
        int[] tCount = new int[128];    // 创建数组统计t中字符出现的次数（ASCII字符集大小128）
        for (char c : t.toCharArray()) {
            tCount[c]++; // 统计t中字符出现的次数
        }
        char[] sArray = s.toCharArray(); // 将s转换为字符数组，便于索引访问
        int required = 0;  // t中不同字符的种类数（
        for (int count : tCount) {
            if (count > 0) {
                required++;
            }
        }
        int formed = 0; // 当前窗口中满足数量要求的字符种类数
        int[] windowCounts = new int[128]; // 统计当前窗口中各字符出现的次数

        // ans[0] 是最小窗口的起始索引，ans[1] 是最小窗口的长度
        int[] ans = {-1, Integer.MAX_VALUE};

        int left = 0, right = 0;
        while (right < s.length()){
            //1.扩展窗口：将right指向的字符加入窗口
            char c = sArray[right];
            windowCounts[c]++; // 窗口包含该字符
            //只有当c是t中字符，且当前窗口中c的计数刚好等于t中c的计数时
            if (tCount[c]>0 && windowCounts[c] == tCount[c]){
                formed++;
            }
            //2.缩小窗口 当窗口包含所有t中字符（formed == required）时
            while (left <= right && formed == required){
                // 更新最小窗口：如果当前窗口更小，则记录
                if (ans[1] > right - left + 1){
                    ans[0] = left; // 记录最小窗口的起始索引
                    ans[1] = right - left + 1;   // 更新窗口长度
                }
                // 3. 收缩窗口：移除left指向的字符
                char d = sArray[left];
                // 关键：只有当d是t中字符，且当前窗口中d的计数刚好等于t中d的计数时
                if (windowCounts[d] >0 && windowCounts[d]== tCount[d]){
                    formed--;
                }
                windowCounts[d]--; // 窗口移除该字符
                left++; // 缩小窗口
            }
            right++;   // 4. 右指针右移，扩展窗口
        }
        return ans[0] == -1 ? "" : s.substring(ans[0], ans[0] + ans[1]);
    }
}
