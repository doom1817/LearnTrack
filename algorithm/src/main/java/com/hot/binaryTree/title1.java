package com.hot.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/03/04/22:32
 * @description:
 * 力扣102. 二叉树的层序遍历
 */
public class title1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrder(root));
    }
    /**
     * 层序遍历
     * 顺序就是根节点，然后左右子节点，然后左右子节点的子节点
     * 将子节点放到队列中，然后取出队列中的节点，将节点的左右子节点放到队列中，然后取出队列中的节点，直到队列为空
     * @param root
     * @return
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for(int i = 0; i < levelSize; i++){
                TreeNode node = queue.remove(0); // 删除并返回第一个元素 符合层序遍历要求(FIFO先进先出)
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(levelList);
        }
        return res;
    }
}
