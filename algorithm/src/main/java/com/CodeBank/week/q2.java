package com.CodeBank.week;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/05/24/21:25
 * @description:
 */
public class q2 {
    private static int passwordStrength(String password) {
        boolean[] seen = new boolean[128];
        int score = 0;
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if (!seen[c]){
                seen[c] = true;
                if (c >= 'a' && c <= 'z') {
                    score += 1;
                } else if (c >= 'A' && c <= 'Z') {
                    score += 2;
                } else if (c >= '0' && c <= '9') {
                    score += 3;
                } else if (c == '!' || c == '@' || c == '#' || c == '$') {
                    score += 5;
                }
            }
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(passwordStrength("aA1!")); //11
    }
}
