package com.DP.day11;

import com.DP.TreeNode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/11/13:10
 * @Description:
 *  力扣337. 打家劫舍 III
 */
public class title {
    public static void main(String[] args) {
        System.out.println(rob(new TreeNode(3, new TreeNode(2, new TreeNode(3), null), new TreeNode(3, null, new TreeNode(1)))));
        System.out.println(rob(new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(3)), new TreeNode(5, null, new TreeNode(1)))));
    }
    private static int rob(TreeNode root){
        int[] res = dfs( root);
        return Math.max(res[0], res[1]);
    }
    private static int[] dfs(TreeNode root){
        if (root == null) return new int[]{0,0};
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int rob = root.val + left[1] + right[1];
        int not_rob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, not_rob};
    }
}
