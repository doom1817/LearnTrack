package com.CodeBank.week500;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/07/09:38
 * @description:
 */
public class q1 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countOppositeParity(new int[]{1, 2, 3, 4}))); // [2,1,1 ,0]
    }
    public static  int[] countOppositeParity(int[] nums) {
         int n = nums.length;
         int[] answer = new int[n];
         for (int i = 0; i < n; i++) {
            int score = 0;
             // 偶数
             for (int j = i + 1; j < n; j++) {
                 if ( ((nums[i] % 2) ^ (nums[j] % 2)) == 1 ) {
                     score++;
                 }
             }
             answer[i] = score;
         }
         return answer;
    }
}
