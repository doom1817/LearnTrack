package com.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/04/19/15:23
 * @description:
 *  力扣1855.下标对中的最大距离
 */
public class day19 {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5})); //2
        System.out.println(maxDistance(new int[]{2,2,2}, new int[]{10,10,1})); //1
        System.out.println(maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,11,10})); //2
    }
    public static int maxDistance(int[] nums1, int[] nums2) {
        int maxDiff = 0; // 记录最大距离
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] <= nums2[j]){ // nums1[i] <= nums2[j]
                maxDiff = Math.max(maxDiff, j - i);
                j++;
            }else {
                i++;
            }
        }
        return maxDiff;
    }
}
