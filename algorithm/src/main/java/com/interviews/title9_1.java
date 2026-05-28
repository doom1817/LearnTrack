package com.interviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: doom
 * @date: 2026/05/28/11:45
 * @description:
 *
 */
public class title9_1 {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
    private static boolean isValid(String s) {
        if (s.length() % 2 != 0){return false;}
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                deque.push(c);
            }else if (c == ')'){
                if (deque.isEmpty() || deque.pop() != '('){
                    return false;
                }
            }else if (c == ']'){
                if (deque.isEmpty() || deque.pop() != '['){
                    return false;
                }
            }else if (c == '}'){
                if (deque.isEmpty() || deque.pop() != '{'){
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}
