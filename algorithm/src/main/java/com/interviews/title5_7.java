package com.interviews;

import  java.util.Map;

/**
 * @author: doom
 * @date: 2026/05/20/10:25
 * @description:
 */
public class title5_7 {
    public static void main(String[] args) {
        System.out.println(romanToInt( "III")); //3
    }
    static Map<Character, Integer> map = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );
    private static int romanToInt(String s){
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            //如果当前值小于下一个值，则减去当前值
            if(i<n-1 && value<map.get(s.charAt(i))){
                result-=value;
            }else {
                result+=value;
            }
        }
        return result;
    }
}
