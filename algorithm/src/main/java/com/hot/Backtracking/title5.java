package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/15/09:14
 * @description:
 * 力扣22.括号生成
 */

public class title5 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        List<Integer> path = new ArrayList<>();
        backtrack(res, path, 0, 0, n);
        return res;
    }
    private static void backtrack(List<String> res, List<Integer> path, int i, int balance, int n) {
        //终止条件
        if (path.size() == n){
            char[] s = new char[n*2];
            Arrays.fill(s,')');
            for (int j:path)
            {
                s[j] = '(';
            }
            res.add(new String(s));
            return;
        }
        for (int right = 0; right <= balance; right++){
            path.add(right+i);
            backtrack(res, path, i+right+1, balance-right+1, n);
            path.remove(path.size()-1);
        }
    }

}
