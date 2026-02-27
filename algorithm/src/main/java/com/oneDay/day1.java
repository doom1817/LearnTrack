package com.oneDay;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/18/08:58
 * @Description:
 *  力扣693.交替位二进制数
 */
public class day1 {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
        System.out.println(hasAlternatingBits1(5));
    }
    public static boolean hasAlternatingBits(int n) {
//        int res = n^(n>>1);
//        return (res&(res+1))==0;
        String str = Integer.toBinaryString(n);
        for (int i = 1; i < str.length(); i++){
            if (str.charAt(i) == str.charAt(i-1)){
                return false;
            }
        }
        return true;
    }

    public static boolean hasAlternatingBits1(int n) {
        int pre= -1 ;
        while (n > 0){
            int cur = n %2;
            if (pre == cur){
                return false;
            }
            pre = cur;
            n /= 2;
        }
        return true;
    }
}
