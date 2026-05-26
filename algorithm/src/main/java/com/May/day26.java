package com.May;

/**
 * @author: doom
 * @date: 2026/05/26/09:04
 * @description:
 *  力扣3120.统计特殊字母的数量Ⅰ
 */
public class day26 {
    public static void main(String[] args) {
        System.out.println(numberOfSpecialChars("aaAbcBC"));
        System.out.println(numberOfSpecialChars("abBCab"));
    }
    public static int numberOfSpecialChars(String word) {
        int ans = 0;
        for (char c='a';c<='z';c++){
            boolean t1 =false,t2 =false;
            for (char ch:word.toCharArray()){
                if (ch == c) t1=true;
                if (c-32 == ch)t2=true;
            }
            if (t1 && t2) ans++;
        }
        return ans;
    }
}
