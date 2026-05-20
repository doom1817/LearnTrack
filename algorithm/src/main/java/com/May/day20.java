package com.May;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/05/20/09:04
 * @description:
 *  力扣2657. 找到两个数组的前缀公共数组
 */
public class day20 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{1,3,2,4}, new int[]{3,1,2,4})));//[0,2,3,4]
        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{2,3,1}, new int[]{3,1,2})));//[0,1,3]
    }

    private static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];  // 统计数字出现频次
        int[] ans = new int[n];
        int commonCount = 0;
        for (int i = 0; i < n; i++){
            freq[A[i]]++;
            if (freq[A[i]] == 2){
                commonCount++;
            }
            freq[B[i]]++;
            if (freq[B[i]] == 2){
                commonCount++;
            }
            ans[i] = commonCount;
        }
        return ans;

    }
}
