package com.hot.greedy;

/**
 * @author: doom
 * @date: 2026/04/24/09:37
 * @description:
 *  力扣45.
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));//2
    }
    public static int jump(int[] nums){
        int n = nums.length;
        if (n==0) return 0;
        int minCount = 0; //最小跳跃次数
        int maxReach = 0;// 最远到达距离
        int cur = 0 ; // 当前这一跳能到达的边界
        for (int i=0;i<n-1;i++){
            maxReach = Math.max(maxReach,i+nums[i]);
            if (cur == i) {
                minCount++;
                cur = maxReach;
                // 终止条件
                if (cur>=n-1){
                    break;
                }
            }
        }
        return minCount;
    }
}
