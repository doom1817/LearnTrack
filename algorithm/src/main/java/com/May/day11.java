package com.May;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/11/09:02
 * @description:
 *  力扣2553.
 */
public class day11 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(separateDigits(new int[]{13,25,83,77}))); //[1,3,2,5,8,3,7,7]
    }
    private static int[] separateDigits(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        for (int num: nums){
            String numStr = String.valueOf(num);
            for(char c: numStr.toCharArray()){
                resultList.add(c - '0');
            }
        }
        int[] answer = new int[resultList.size()];
        for (int i = 0; i < answer.length; i++){
            answer[i] = resultList.get(i);
        }
        return answer;
    }
}
