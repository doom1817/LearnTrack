package com.DP.day10;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/11/11:08
 * @Description:
 * 力扣96. 不同的二叉搜索树
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(numTrees1(3));
        System.out.println(numTrees1(1));
    }
    /** 动态规划
     * 递推公式：dp[i] = dp[j-1] * dp[i-j]
     * dp[i] 表示节点数为 i 时的二叉搜索树数量
     * dp[j-1] 表示以 j 为根节点时的左子树组合数
     * dp[i-j] 表示以 j 为根节点时的右子树组合数
     */
    private static int numTrees(int n) {
        // 创建一个长度为 n+1 的数组 dp，用来存储不同节点数对应的二叉搜索树数量
        int[] dp = new int[n + 1];

        // 初始化 dp[0] = 1，表示空树也是一种情况
        dp[0] = 1;

        // 外层循环从 2 到 n，依次计算节点数为 i 时的二叉搜索树数量
        for (int i = 2; i <= n; i++) {
            // 内层循环遍历每个可能的根节点 j（从 1 到 i）
            for (int j = 1; j <= i; j++) {
                // 对于根节点 j，左子树有 j-1 个节点，右子树有 i-j 个节点
                // dp[j-1] * dp[i-j] 表示以 j 为根节点时的左右子树组合数
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        // 返回节点数为 n 时的二叉搜索树总数
        return dp[n];
    }
    /** 卡特兰数
     * 递推公式：c = c * 2 * (2i + 1) / (i + 2)
     * c 表示节点数为 i 时的二叉搜索树数量
     * i 表示节点数
     */
    private static int numTrees1(int n) {
        long c = 1;
        for (int i=0;i<n;i++){
            c = c * 2 * (2L * i + 1) / (i + 2);
        }
        return (int) c;
    }
}
