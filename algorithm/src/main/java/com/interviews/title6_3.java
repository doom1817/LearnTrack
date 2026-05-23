package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/22/10:32
 * @description:
 *  力扣98. 验证二叉搜索树
 */
public class title6_3 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private static boolean isValidBST(TreeNode root, long min, long max) {
        // 递归终止条件
        if (root == null){
            return true;
        }
        if (root.val <= min || root.val >= max){
            return false;
        }
        // 递归调用
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(isValidBST(root));
    }
}
