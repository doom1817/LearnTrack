package com.hot.binaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/03/09/20:14
 * @description:
 * 力扣437. 路径总和 III
 */
public class title7 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);
        System.out.println(pathSum(root, 8));
    }
    public static int pathSum(TreeNode root, int targetSum) {
        // 前缀和哈希表，用于记录路径和出现次数
        Map<Long,Integer> prefixSum = new HashMap<>();
        prefixSum.put(0L,1);//初始化前缀和
        return dfsWithPrefixSum(root, 0, targetSum, prefixSum);
    }
    /**
     * 递归函数：使用前缀和+哈希表计算路径数量
     * @param root 当前节点
     * @param currSum 当前路径和（从根节点到当前节点的和）
     * @param targetSum 目标路径和
     * @param prefixSum 前缀和哈希表（记录路径和出现的次数）
     * @return 满足条件的路径数量
     */
    private static int dfsWithPrefixSum(TreeNode root, long currSum, int targetSum, Map<Long,Integer> prefixSum) {
        // 递归终止条件：当前节点为空
        if (root == null) {
            return 0;
        }
        currSum += root.val; // 更新当前路径和：加上当前节点的值
        // 查找是否存在前缀和为 (currSum - targetSum) 的路径
        int res = prefixSum.getOrDefault(currSum - targetSum, 0);
        // 更新前缀和
        prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);
        res += dfsWithPrefixSum(root.left, currSum, targetSum, prefixSum);
        res += dfsWithPrefixSum(root.right, currSum, targetSum, prefixSum);
        // 回溯
        prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) - 1);
        return res;
    }
}
