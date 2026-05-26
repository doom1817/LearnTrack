package com.interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/05/26/10:06
 * @description:
 *  力扣242. 有效的字母异位词
 */
public class title7_2 {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));// true
        System.out.println(isAnagram("rat", "car"));// false
    }
    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++){
            arr[s.charAt(i)-'a']++;
            arr[t.charAt(i)-'a']--;
        }
        for (int j : arr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
