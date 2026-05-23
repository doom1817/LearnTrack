package com.interviews;

import com.hot.binaryTree.TreeNode;

import java.util.Stack;

/**
 * @author: doom
 * @date: 2026/05/22/10:21
 * @description: 力扣230. 二叉搜索树中第K小的元素
 */
public class title6_2 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(root);
    }
    private int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()){
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();//当前节点为null时，说明已经遍历完左子树，此时从栈中弹出栈顶元素，并访问该元素。
            k--;
            if (k == 0){
                return curr.val;
            }
            curr = curr.right;
        }
        return -1;
    }
}
