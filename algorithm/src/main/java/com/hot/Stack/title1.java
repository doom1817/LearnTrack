package com.hot.Stack;

import java.util.Stack;

/**
 * @author: doom
 * @date: 2026/04/21/09:30
 * @description:
 *  力扣20.有效的括号
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
    // 栈 特点:先进后出
    private static boolean isValid(String s) {
        if (s.length() % 2 != 0)return false;
        Stack<Character> stack = new Stack<>();
        // 将字符串中的字符放入栈中
        for(int i = 0; i < s.length(); i++){
            char c= s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else if (c == ')'){
                if (stack.isEmpty() || stack.pop()!='('){
                    return false;
                }
            }else if (c == ']'){
                if (stack.isEmpty() || stack.pop()!='['){
                    return false;
                }
            }else if (c == '}'){
                if (stack.isEmpty() || stack.pop()!='{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
