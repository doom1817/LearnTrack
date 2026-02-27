package com.DP.day8;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/08/19:34
 * @Description:
 * 力扣1035. 不相交的线
 */
public class title3 {

    public static void main(String[] args) {
        System.out.println(maxUncrossedLines2(new int[]{1,4,2}, new int[]{1,2,4}));
        System.out.println(maxUncrossedLines2(new int[]{2,5,1,2,5}, new int[]{10,5,2,1,5,2}));
        System.out.println(maxUncrossedLines2(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1}));
    }
    /**
     * 常规解法，题目本质就是最长公共子序列的变种
     * @param nums1
     * @param nums2
     * @return
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                // 如果两个数字相等，则 dp[i][j] = dp[i-1][j-1] + 1
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 否则，则 dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    /**
     * 优化解法，使用滚动数组 空间压缩
     * @param nums1
     * @param nums2
     * @return
     */
    public static int maxUncrossedLines2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m<n)return maxUncrossedLines2(nums2, nums1); // 保证 nums2最小
        int[] dp = new int[n + 1]; //dp[j] 表示当前行处理到 nums2 的第 j 个元素时的 LCS 长度

        for (int i = 1; i <= m; i++) {
            int prev = 0; // 记录上一行 dp[j] 的值
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (nums1[i - 1] == nums2[j - 1]){
                    dp[j] = prev + 1;
                }else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                prev = temp;
            }
        }
        return dp[n];
    }
}
