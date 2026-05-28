package com.May;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/05/27/09:13
 * @description:
 * 力扣3121. 统计特殊字母的数量Ⅱ
 */
public class day27 {
    public static void main(String[] args) {
        System.out.println(numberOfSpecialChars("aaAbcBC"));//3
        System.out.println(numberOfSpecialChars("abc")); //0
    }

    /**
     *  特殊字母满足条件：
     *  要同时有这个字母的大小写
     *  小写先出现，大写后出现
     * @param word
     * @return
     */
    private static int numberOfSpecialChars(String word) {
        if (word == null || word.isEmpty()){
            return 0;
        }
        int[] lowerCaseFirstPos = new int[26];
        Arrays.fill(lowerCaseFirstPos, -1);
        int[] upperCaseFirstPos = new int[26];
        Arrays.fill(upperCaseFirstPos, -1);
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char c  = chars[i];
            if (Character.isLowerCase(c)){
                lowerCaseFirstPos[c-'a'] = i;
            }else{
                int idx = c - 'A';
                if (upperCaseFirstPos[idx] == -1) {
                    upperCaseFirstPos[idx] = i;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (lowerCaseFirstPos[i] != -1 && upperCaseFirstPos[i] != -1 && lowerCaseFirstPos[i] < upperCaseFirstPos[i]){
                count++;
            }
        }
        return count;
    }
}
