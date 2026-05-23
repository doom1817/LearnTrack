package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/22/10:03
 * @description: 力扣530.二叉搜索树的最小绝对差
 */
public class title6_1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(getMinimumDifference(root));
    }

    public static int getMinimumDifference(TreeNode root) {
        int res = Integer.MAX_VALUE;
        TreeNode pre = null;
        while (root != null) {
            if (root.left == null) {
                if (pre != null) {
                    res = Math.min(res, root.val - pre.val);
                }
                pre = root;
                root = root.right;
            } else {
                TreeNode node = root.left;
                while (node.right != null && node.right != root) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = root;
                    root = root.left;
                } else {
                    node.right = null;
                    if (pre != null) {
                        res = Math.min(res, root.val - pre.val);
                    }
                    pre = root;
                    root = root.right;
                }
            }
        }
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
