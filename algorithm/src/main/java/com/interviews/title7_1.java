package com.interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/05/26/09:20
 * @description:
 *  力扣290. 单词规律
 */
public class title7_1 {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat fish"));//false
        System.out.println(wordPattern("abba", "dog cat cat dog")); //true
    }
    //1.使用map 的k-v对比；2.使用双向map对比
    private static boolean  wordPattern(String pattern, String s) {
        String[] sArray = s.split(" ");
        if (pattern.length() != sArray.length) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++){
            char temp = pattern.charAt(i);
            if (map.containsKey(temp)){
                if (!map.get(temp).equals(sArray[i])) return false;
            }else {
                if (map.containsValue(sArray[i])) return false;
                map.put(temp, sArray[i]);
            }
        }
        return true;
    }
}
