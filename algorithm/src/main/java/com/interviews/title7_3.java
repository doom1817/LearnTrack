package com.interviews;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/05/26/10:15
 * @description:
 *  力扣49. 字母异位词分组
 */
public class title7_3 {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        if (strs.length == 1) return new ArrayList<>(List.of(List.of(strs[0])));
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            if (str ==null)continue;
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
