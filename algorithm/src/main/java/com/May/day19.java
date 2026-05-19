package com.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/19/09:03
 * @description:
 *  力扣2540.最小公共值
 */
public class day19 {
    public static void main(String[] args) {
        System.out.println(getCommon(new int[]{1,2,3}, new int[]{2,4}));//2
        System.out.println(getCommon(new int[]{1,2,3,6}, new int[]{2,3,4,5}));//-1
    }
    private static int getCommon(int[] nums1, int[] nums2) {
        for (int i = 0, j = 0; i < nums1.length; i++) {
            while (j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    return nums1[i];
                }
                if (nums1[i] > nums2[j]) {
                    j++;
                    continue;
                }
                if (nums1[i] < nums2[j]) {
                    break;
                }
            }
        }
        return -1;
    }
}
