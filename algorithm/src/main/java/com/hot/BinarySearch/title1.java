package com.hot.BinarySearch;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/18/13:51
 * @description:
 *  力扣35. 搜索插入位置
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5)); //2
    }
    public static int searchInsert(int[] nums, int target) {
        int idx= Arrays.binarySearch(nums, target);
        return  idx < 0 ? -1 - idx : idx;
    }

}
