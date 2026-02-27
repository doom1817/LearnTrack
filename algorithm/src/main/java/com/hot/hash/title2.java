package com.hot.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/19/16:18
 * @Description:
 * 力扣49. 字母异位词分组
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)return new ArrayList<>();
        return new ArrayList<>(Arrays.stream(strs)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(str ->
                        new String(str.chars()
                                .sorted()
                                .collect(StringBuilder::new,
                                        StringBuilder::appendCodePoint,
                                        StringBuilder::append)
                                .toString())
                )).values());

    }
}
