package com.April;

import java.util.HashMap;
import java.util.Map;

//力扣3740.三个相等元素之间的最小距离Ⅰ
public class day10 {
    public static void main(String[] args) {
        System.out.println(minimumDistance(new int[]{1,2,1,1,3})); //6
        System.out.println(minimumDistance(new int[]{1,1,2,3,2,1,2})); //8
        System.out.println(minimumDistance(new int[]{1})); //-1
    }
    
    private static int minimumDistance(int[] nums) {
    Map<Integer, int[]> map = new HashMap<>();
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
        int[] lastTwo = map.getOrDefault(nums[i], new int[]{-1, -1});
        if (lastTwo[0] != -1 && lastTwo[1] != -1) {
            // 三个下标分别为 lastTwo[0], lastTwo[1], i
            minDist = Math.min(minDist, 2 * (i - lastTwo[0]));
        }
        // 更新最近两个下标
        lastTwo[0] = lastTwo[1];
        lastTwo[1] = i;
        map.put(nums[i], lastTwo);
    }
    return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
