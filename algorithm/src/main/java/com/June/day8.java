package com.June;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/06/08/08:57
 * @description:
 *  力扣2161. 根据给定数字划分数组
 */
public class day8 {
    /**
     *  1.进行重新排序，小于pivot的数字放在左边，大于pivot的数字放在右边
     *  2.大于的相对排序不变，小于的相对位置排序不变
     *  解题思路：
     *  就是创建二个数组用于存二种： 小于的，大于的
     *  后续再创建一个好的进行拼接相加
     * @param nums
     * @param pivot
     * @return
     */
    private static int[] pivotArray(int[] nums, int pivot) {
        List<Integer> less = new ArrayList<>();
        List<Integer> more = new ArrayList<>();
        int repeatTimes = 0;//相等的与pivot的次数
        for (int num : nums) {
            if (num < pivot){
                less.add(num);
            }
            if (num > pivot){
                more.add(num);
            }else if (num == pivot){
                repeatTimes++;
            }
        }
        int[] res = new int[nums.length];
        int index = 0; // 全局索引
        // 填充小于 pivot 的部分
        for (int num : less) {
            res[index++] = num;
        }
        // 填充等于 pivot 的部分
        for (int i = 0; i < repeatTimes; i++) {
            res[index++] = pivot;
        }
        // 填充大于 pivot 的部分
        for (int num : more) {
            res[index++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10)));
    }
}
