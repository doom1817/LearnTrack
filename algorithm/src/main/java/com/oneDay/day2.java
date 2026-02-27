package com.oneDay;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/19/15:19
 * @Description:
 * 每日一题：力扣696. 计数二进制子串
 */
public class day2 {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110011"));
        System.out.println(countBinarySubstrings("10101"));
    }
    public static int countBinarySubstrings(String s) {
        int prev = 0, cur = 1;
        int ans = 0;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == s.charAt(i - 1)){
                cur++;
            }
            else{
                ans+=Math.min(prev, cur);
                prev = cur;
                cur = 1;
            }
        }
        ans+=Math.min(prev, cur);
        return ans;
    }
}
