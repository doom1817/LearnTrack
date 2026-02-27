package com.hot.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/22/16:45
 * @Description:
 *  力扣438. 找到字符串中所有字母异位词
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        if (n < m) return res;
        int[] cntP = new int[26];
        int[] cntS = new int[26];
        //预处理 ，统计p中每个字母出现的次数，创建一个长度为26的数组cntP
        for (int i = 0; i < m; i++) {
            cntP[p.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;//滑动窗口的左右边界

        for (;right < n; right++){
            cntS[s.charAt(right) - 'a']++;
            if (right - left + 1 == m){
                if (Arrays.equals(cntP, cntS)){
                    res.add(left);
                }
                cntS[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return res;
    }
}
