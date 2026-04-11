package com.April;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/04/11/21:15
 * @description:
 *  力扣3741. 三个相等元素之间的最小距离 Ⅱ
 */
public class day11 {


    public static void main(String[] args) {
        System.out.println(minimumDistance(new int[]{
                1,2,1,1,3
        })); //6
    }

    public static int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            map.computeIfAbsent(nums[i], v -> new ArrayList<>()).add( i);
        }

        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()){
            if (list.size() <3)continue;
            for (int i = 0;i+2<list.size();i++){
                int left = list.get(i);
                int right = list.get(i+2);
                ans = Math.min(ans, 2*(right-left));
            }

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
