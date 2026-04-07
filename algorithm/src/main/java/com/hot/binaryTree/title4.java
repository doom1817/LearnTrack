package com.hot.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/03/05/09:52
 * @description:
 * 力扣199. 二叉树的右视图
 */
public class title4 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        System.out.println(rightSideView(root));
    }
    private static List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null){return res;}
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);// 将根节点入队
        // 当队列不为空时，说明还有层未遍历
        while (!queue.isEmpty()) {
            // 获取当前层的节点数量
            // 这步非常关键，因为我们在循环中会向 queue 添加下一层的节点，
            // 必须固定当前层的大小，避免将下一层节点混入当前层的处理逻辑
            int levelSize = queue.size();
            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++){
                TreeNode node = queue.remove(0);//移除列表第一个元素（模拟队列 FIFO）
                //如果是当前层的最后一个节点 (索引为 levelSize - 1)这就是从右侧能看到的节点
                if (i == levelSize - 1){
                    res.add(node.val);
                }
                // 将左子节点入队（如果存在）
                if (node.left != null){
                    queue.add(node.left);
                }
                // 将右子节点入队（如果存在）
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return res;
    }
}
