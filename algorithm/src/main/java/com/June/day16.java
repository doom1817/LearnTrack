package com.June;

/**
 * @author: doom
 * @date: 2026/06/16/09:03
 * @description:
 *  力扣3612. 用特殊操作处理字符串Ⅰ
 */
public class day16 {
    public static void main(String[] args) {
        System.out.println(processString("a#b%*"));
    }
    private static String processString(String s){
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char aChar : chars) {
            //如果是小写英文字母
            if (aChar >= 'a' && aChar <= 'z') {
                result.append(aChar);
            } else if (aChar == '#') {
                result.append(result);
            } else if (aChar == '*') {
                if (!result.isEmpty()) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else if (aChar == '%') {
                result.reverse();
            }
        }
        return result.toString();
    }
}
