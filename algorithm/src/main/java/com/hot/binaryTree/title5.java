package com.hot.binaryTree;

/**
 * @author: doom
 * @date: 2026/03/05/10:57
 * @description:
 *  力扣114. 二叉树展开为链表
 }
 */
public class title5 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        flatten(root);
    }
    //先序排序
    private static void flatten(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null){
                TreeNode next = node.left;
                while (next.right != null){
                    next = next.right;
                }
                next.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
}
