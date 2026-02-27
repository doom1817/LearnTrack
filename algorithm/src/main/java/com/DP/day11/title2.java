package com.DP.day11;

import com.DP.TreeNode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/15/11:31
 * @Description:
 * 力扣124. 二叉树中的最大路径和
 */
public class title2 {


    public static void main(String[] args) {
        System.out.println(maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        System.out.println(maxPathSum(new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
    }

    private  static  int maxSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    // 要找到一条路径，使得该路径上所有节点的值之和最大
    public static int maxGain(TreeNode node) {
        if (node == null) return 0;
        //实现node+max(left, right) 是最大的且连续的
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);
        int currentPathSum = node.val+left+right;
        maxSum = Math.max(maxSum, currentPathSum);
        return node.val+Math.max(left, right);
    }
}
