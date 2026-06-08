package com.June;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: doom
 * @date: 2026/06/07/20:35
 * @description:
 *  力扣2196. 根据描述创建二叉树
 */
public class day7 {
    private static  class TreeNode {
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

    public static void main(String[] args) {
        System.out.println(createTree(new int[][]{{20,15,1}, {20,17,0}, {50,20,1},{50,80,0}}));

    }
    private static TreeNode createTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parentVal = desc[0];   // 父节点的值
            int childVal  = desc[1];   // 子节点的值
            int isLeft    = desc[2];   // 1表示左孩子，0表示右孩子

            // 获取父节点对象：如果尚未创建，则新建并存入 map
            TreeNode parent = nodeMap.computeIfAbsent(parentVal, k -> new TreeNode(parentVal));
            // 获取子节点对象：同理
            TreeNode child  = nodeMap.computeIfAbsent(childVal,  k -> new TreeNode(childVal));

            // 建立连接
            if (isLeft == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            // 记录所有子节点，用于后续找根节点
            children.add(childVal);
        }

        // 根节点就是所有节点中，唯一一个没有出现在 children 集合中的节点
        // 遍历 nodeMap 的键集（即所有节点的值）
        for (int val : nodeMap.keySet()) {
            if (!children.contains(val)) {
                return nodeMap.get(val);
            }
        }

        return null; // 根据题意，测试用例保证有根，不会走到这里
    }
}
