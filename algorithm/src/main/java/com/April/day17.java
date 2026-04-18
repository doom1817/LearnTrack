package com.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/04/17/09:34
 * @description:
 *  力扣3761. 镜像对之间最小绝对距离
 */
public class day17 {
    public static void main(String[] args) {
        System.out.println(minMirrorPairDistance(new int[]{12,21,45,33,54})); //1
        System.out.println(minMirrorPairDistance(new int[]{120,21})); //1
        System.out.println(minMirrorPairDistance(new int[]{21,120})); //-1
    }

    public static int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastPos = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // 如果之前存在 j，使 reverse(nums[j]) == nums[i]
            Integer j = lastPos.get(nums[i]);
            if (j != null) {
                ans = Math.min(ans, i - j);
            }

            // 只计算一次 reverse
            int rev = reverse(nums[i]);

            // 关键：只保留最近的下标（保证距离最小）
            lastPos.put(rev, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int reverse(int num){
        int res = 0;
        while (num != 0){
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}
