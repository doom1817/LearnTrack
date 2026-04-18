package com.algorithm;

import java.util.Arrays;
/**
 * 题目描述
给定一个四位整数 num（1000 ≤ num ≤ 9999），且该数字的四个数字不完全相同。
对该数字重复执行如下操作，直到结果为 6174：
将数字的四位数字按降序排列，组成最大的四位数；
将数字的四位数字按升序排列，组成最小的四位数（不足四位时前面补 0，保持四位）；
用最大数 − 最小数，得到一个新的四位数。
请实现一个函数，返回从输入数字到得到 6174 所需要执行的操作次数。
示例 1
输入：
num = 1234
输出：
3
解释：
4321 − 1234 = 3087
8730 − 0378 = 8352
8532 − 2358 = 6174
共 3 次操作。
示例 2
输入：
num = 6174
输出：
0
解释：
已经是 6174，无需操作。
示例 3
输入：
num = 2026
输出：
6
提示
输入保证是合法四位整数，且四个数字不全相同；
每次计算都要严格保持四位，不足四位补前导 0；
只要结果变为 6174，立即停止并返回次数。
 */
public class kaprekar {
    public static void main(String[] args) {
        System.out.println(kaprekarCount(1234)); // 3
        System.out.println(kaprekarCount(4321)); // 3
        System.out.println(kaprekarCount(6174)); // 0
        System.out.println(kaprekarCount(1000)); // 5
    }

    //常规解法：字符串处理，排序，反转
    private static int kaprekarCount(int num) {
      if (num == 6174) return 0;
      int count = 0;
      while (num != 6174) {
          char[] digits = String.format("%04d", num).toCharArray();
          Arrays.sort(digits);
          int min = Integer.parseInt(new String(digits));
          int max = Integer.parseInt(new StringBuilder(new String(digits)).reverse().toString());
          num = max - min;
          count++;
      }
      return count;
    }
    //纯数值解法
    private static int kaprekarCount2(int num) {
        if (num < 0 || num > 9999) return -1;
        if (num == 6174) return 0;

        int count = 0;
        while (num != 6174) {
            int a = num / 1000;
            int b = num / 100 % 10;
            int c = num / 10 % 10;
            int d = num % 10;

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a > c) {
                int temp = a;
                a = c;
                c = temp;
            }
            if (a > d) {
                int temp = a;
                a = d;
                d = temp;
            }
            if (b > c) {
                int temp = b;
                b = c;
                c = temp;
            }
            if (b > d) {
                int temp = b;
                b = d;
                d = temp;
            }
            if (c > d) {
                int temp = c;
                c = d;
                d = temp;
            }

            int min = a * 1000 + b * 100 + c * 10 + d;
            int max = d * 1000 + c * 100 + b * 10 + a;
            num = max - min;

            if (num == 0) return -1;
            count++;
        }
        return count;
    }
}
