package com.oneDay;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/02/24/15:25
 * @description:
 * 力扣1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值或为 0 或为 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个 32-bit 整数。
 */
public class day6 {

    /**
     * 二叉树节点定义
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 主函数，用于执行和测试
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        // 测试用例 1: [1,0,1,0,1,0,1] -> 22
        //       1
        //      / \
        //     0   1
        //    / \ / \
        //   0  1 0  1
        // 路径: 1->0->0 (4), 1->0->1 (5), 1->1->0 (6), 1->1->1 (7). 总和: 4+5+6+7=22
        TreeNode root1 = buildTree(new Integer[]{1, 0, 1, 0, 1, 0, 1});
        System.out.println("输入: [1,0,1,0,1,0,1]");
        System.out.println("输出: " + sumRootToLeaf(root1)); // 预期输出: 22

        // 测试用例 2: [0] -> 0
        TreeNode root2 = new TreeNode(0);
        System.out.println("\n输入: [0]");
        System.out.println("输出: " + sumRootToLeaf(root2)); // 预期输出: 0
    }

    /**
     * 计算从根到所有叶子节点路径所代表的二进制数的总和。
     *
     * @param root 二叉树的根节点
     * @return 所有路径二进制数的总和
     */
    public static int sumRootToLeaf(TreeNode root) {
        // 启动深度优先搜索，初始路径值为 0
        return dfs(root, 0);
    }

    /**
     * 辅助的深度优先搜索（DFS）方法。
     *
     * @param node 当前访问的节点
     * @param val  到达当前节点之前，路径上已形成的二进制数值
     * @return 以当前节点为根的子树中，所有根到叶路径所代表的二进制数的总和
     */
    private static int dfs(TreeNode node, int val) {
        // 如果节点为空，则对总和无贡献，返回 0
        if (node == null) {
            return 0;
        }

        // 更新当前路径的数值：将之前的值左移一位，然后加上当前节点的值
        int currentVal = val << 1 | node.val;

        // 如果当前节点是叶子节点（没有左右子节点）
        if (node.left == null && node.right == null) {
            // 返回当前路径形成的完整二进制数
            return currentVal;
        }

        // 递归计算左右子树的贡献，并返回它们的总和
        return dfs(node.left, currentVal) + dfs(node.right, currentVal);
    }

    /**
     * 根据层序遍历数组构建二叉树（用于测试）。
     * 此方法模拟 LeetCode 的二叉树输入格式。
     *
     * @param arr 层序遍历数组，null 表示空节点
     * @return 构建好的二叉树的根节点
     */
    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // 处理左子节点
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            // 处理右子节点
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}