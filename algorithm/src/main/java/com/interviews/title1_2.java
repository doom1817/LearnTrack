package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/06/10:09
 * @description:
 *  力扣392. 判断子序列
 */
public class title1_2 {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
    }
    private static boolean isSubsequence(String s, String t) {
        int slow = 0;
        int fast = 0;
        while (slow <s.length() && fast <t.length()){
            if (s.charAt(slow) == t.charAt(fast)){
                slow++;
                if (slow == s.length()){
                    return true;
                }
            }
            fast++;
        }
        return slow == s.length();
    }
}
