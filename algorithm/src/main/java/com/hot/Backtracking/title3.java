package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/14/15:20
 * @description:
 *  力扣17.电话号码的字母组合
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations("2")); // ["a","b","c"]
    }

    private static final String[] LETTER_MAP = {
            " ",    // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };
    /**
     * 递归
     */
    public static List<String> letterCombinations1(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.isEmpty()){
            return res;
        }
        res.add("");
        for (int i = 0; i < digits.length(); i++){
            int digit = digits.charAt(i) - '0';
            if (digit>9 || digit<2){
                continue;
            }
            String letters = LETTER_MAP[digit];
            List<String> temp = new ArrayList<>();
            for (String s:res){
                for (char c:letters.toCharArray()){
                    temp.add(s+c);
                }
            }
            res = temp;
        }
        return res;
    }
    /**
     * 回溯
     */
    public static List<String> letterCombinations(String digits) {
        List< String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        //深度搜索
        backtrack(digits, 0, new StringBuilder(), result); //初始化
        return result;
    }
    private static void backtrack(String digits, int index, StringBuilder sb, List<String> result) {
        if (index == digits.length()){
            result.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        String letters = LETTER_MAP[digit];
        for (char c:letters.toCharArray()){
            sb.append( c);
            backtrack(digits, index+1, sb, result); //递归
            sb.deleteCharAt(sb.length()-1); //回溯
        }
    }
}
