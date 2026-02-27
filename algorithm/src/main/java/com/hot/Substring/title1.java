package com.hot.Substring;

import java.util.HashMap;

/**
 * @author: doom
 * @date: 2026/02/23/19:51
 * @description:
 * 力扣560. 和为K的子数组
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1,1,1}, 2));
        System.out.println(subarraySum(new int[]{1,2,3}, 3));
        System.out.println(subarraySum(new int[]{1,2,3}, 5));
    }
    public static int subarraySum(int[] nums, int k) {
        int count = 0,pre = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
