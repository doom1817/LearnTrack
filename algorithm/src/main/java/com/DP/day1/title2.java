package com.DP.day1;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/12/23:06
 * @Description:
 * 力扣740. 删除并获得点数
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2,2,3,3,3,4}));
    }

    /**思路
     * 1.需要创建一个数组，该数组要包含
     *  将问题转化为打家劫舍问题
     *  先统计每个数字出现的次数，计算每个数字的总点数
     *  然后对转换后的数组应用动态规划
     * */
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 找到数组中的最大值，确定辅助数组的大小
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // 创建points数组，points[i]表示选择数字i能得到的总点数
        int[] points = new int[maxVal + 1];
        for (int num : nums) {
            points[num] += num;
        }
        
        // 现在问题变成了打家劫舍问题：不能选择相邻的数字
        int[] dp = new int[maxVal + 1];
        dp[0] = points[0];
        if (maxVal >= 1) {
            dp[1] = Math.max(points[0], points[1]);
        }
        
        for (int i = 2; i <= maxVal; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + points[i]);
        }
        return dp[maxVal];
//  优化将空间复杂度从O(m)降低到O(1)
//        int prev = 0, curr = 0;
//        for (int i = 0; i <= maxVal; i++) {
//            int temp = curr;
//            curr = Math.max(curr, prev + points[i]);
//            prev = temp;
//        }
//
//        return curr;
    }
}
