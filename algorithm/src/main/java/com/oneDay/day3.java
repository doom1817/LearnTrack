package com.oneDay;


import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/15:57
 * @Description:
 * 力扣762. 二进制表示中质数个计算置位
 */
public class day3 {
    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(6, 10));
        System.out.println(countPrimeSetBits(10, 15));
    }

    /**
     * 解题思路：通过遍历left到right的数字，对每个数字进行二进制转换，并统计1的个数，判断是否是质数，并计数
     * @param left
     * @param right
     * @return
     */
    public static int countPrimeSetBits(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++){
            int count = 0;
            // 对i进行位运算
            for (int j = i; j != 0; j&=(j-1)){
                count++;
            }
            // 判断是否是质数
            if (count == 2||count == 3||count == 5||count == 7||count == 11||count == 13||count == 17||count == 19){
                res++;
            }
        }
        System.out.println(res);
        return res;
    }
}
