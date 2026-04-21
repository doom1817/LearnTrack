package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/06/09:57
 * @description:
 *  力扣125. 验证回文串
 */
public class title1_1 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    private static  boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            //持续跳过直到找到有效字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            //比较字符
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
