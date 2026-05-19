package com.interviews;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/05/19/10:07
 * @description:
 */
public class title5_4 {
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{3,0,6,1,5}));//3
    }
    static int hIndex(int[] citations){
        Arrays.sort(citations);
        int n = citations.length;
        for (int i=0;i<n;i++){
            int h = n-i;
            if (citations[i]>=h){
                return h;
            }
        }
        return 0;
    }
}
