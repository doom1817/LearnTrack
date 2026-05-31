package com.interviews;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: doom
 * @date: 2026/05/29/09:45
 * @description:
 *  力扣150. 逆波兰表达式求值
 */
public class title9_3 {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));// 9
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"})); // 6
    }
    private static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens){
            // 判断是否为运算符：长度为1且是 "+-*/" 之一
            if (token.length() == 1 && "+-*/".indexOf(token.charAt(0)) >= 0) {
                int b = stack.pop();
                int a = stack.pop();
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break; // 向零截断
                }
            } else {
                // 数字（包括负数）
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
