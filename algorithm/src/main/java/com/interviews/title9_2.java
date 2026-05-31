package com.interviews;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: doom
 * @date: 2026/05/29/09:26
 * @description:
 *  力扣71. 简化路径
 */
public class title9_2 {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/")); // "/home"
        System.out.println(simplifyPath("/../"));
    }
    private static String simplifyPath(String path) {
        if (path == null || path.isEmpty())return  "";
        Deque<String> stack = new ArrayDeque<>();
        for (String s : path.split("/")){
            if (s.isEmpty() || s.equals(".")){
                continue;
            }else if (s.equals("..")){
                if (!stack.isEmpty()){
                    stack.pollLast();
                }
            }else {
                stack.offerLast(s);
            }
        }
        if (stack.isEmpty()){
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack){
            sb.append("/").append(s);
        }
        return sb.toString();
    }
}
