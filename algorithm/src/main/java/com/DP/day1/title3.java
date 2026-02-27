package com.DP.day1;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/01/12/23:21
 * @Description:
 * 力扣 62. 不同路径
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    /**
     * 思路1：动态规划方法
     * 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     */
    /*
    public static int uniquePaths(int m, int n) {
           int[][]  dp= new int[m][n];
           //初始化 先填充第一行和第一列
           for (int i = 0; i < m; i++) {
               dp[i][0] = 1;
           }
           for (int j = 0; j < n; j++) {
               dp[0][j] = 1;
           }
        //状态转移方程
           for (int i = 1; i < m; i++) {
               for (int j = 1; j < n; j++) {
                   dp[i][j] = dp[i-1][j] + dp[i][j-1];
               }
           }
           return dp[m-1][n-1];
    }
    */
    
    /**
     * 思路2：使用组合数学的方法
     * 从左上角到右下角总共需要走 (m-1)+(n-1) 步
     * 其中 (m-1) 步向下，(n-1) 步向右
     * 问题转化为：在总共 (m+n-2) 步中选择 (m-1) 步向下走的方案数
     * 即 C(m+n-2, m-1) 或者 C(m+n-2, n-1)
     */
    public static int uniquePaths(int m, int n) {
        // 总共需要走的步数
        int totalSteps = m + n - 2;
        // 向下走的步数
        int downSteps = m - 1;
        // 向右走的步数
        int rightSteps = n - 1;
        
        // 为了减少计算量和避免溢出，选择较小的值作为组合数的下标
        int smaller = Math.min(downSteps, rightSteps);
        
        // 计算组合数 C(totalSteps, smaller)
        // 为避免溢出，使用long类型并优化计算顺序
        long result = 1;
        for (int i = 1; i <= smaller; i++) {
            result = result * (totalSteps - i + 1) / i;
            //C(n,k) = C(n, k-1) * (n-k+1) / k
        }
        
        return (int) result;
    }
}
