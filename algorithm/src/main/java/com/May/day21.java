package com.May;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/05/21/09:12
 * @description:
 *  力扣3043. 最长公共前缀的长度
 */
public class day21 {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new int[]{1,10,100}, new int[]{1000}));//3
        System.out.println(longestCommonPrefix(new int[]{1,2,3}, new int[]{4,4,4})); //0
    }

    /**
     *  就是统计两个数组中开头相同数字的个数
     *  换言之就是比较两个数组中开头相同的数字个数，先从高位开始比较
     * @param arr1
     * @param arr2
     * @return
     */
    private static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> st = new HashSet<>();
        for (int i : arr1){
            for (;i>0;i/=10){
                st.add(i);
            }
        }
        int ans = 0;
        for (int i : arr2){
            while (i>0 && !st.contains(i)){
                i/=10;
            }
            ans = Math.max(ans, i);
        }
        return ans>0?Integer.toString(ans).length():0;
    }
}
