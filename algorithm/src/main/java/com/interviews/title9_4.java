package com.interviews;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: doom
 * @date: 2026/05/29/09:45
 * @description:
 *  力扣224. 基本计算器
 */
public class title9_4 {
    public static void main(String[] args) {
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));// 23
        System.out.println(calculate("1 + 1")); // 2
    }

    private static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sign = 1,res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                int sum = c - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))){
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                res += sum * sign;
            }else if (c == '+'){
                sign = 1;
            }else if (c == '-'){
                sign = -1;
            }else if (c == '('){
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            }else if (c == ')'){
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }
}
