package com.hot.binaryTree;

/**
 * @author: doom
 * @date: 2026/03/05/08:55
 * @decription:
 *  力扣124. 二叉树中的最大路径和
 */
public class title3 {
    private static int maxSum = Integer.MIN_VALUE;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));
    }
    private static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxGain(root);
        return maxSum;
    }
    private static int maxGain(TreeNode node) {
        if (node == null) {return 0;}
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        int priceNewPath = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, priceNewPath);
        return node.val + Math.max(leftGain, rightGain);
    }
}
