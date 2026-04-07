package com.hot.binaryTree;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/03/09/08:50
 * @description:
 * 力扣105. 从前序与中序遍历序列构造二叉树
 */
public class title6 {
    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root);
    }
    /**
     * 递归
     *  preorder: 根左右
     *  inorder: 左根右
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(preorder[0]); // 根节点
        int index = 0;
        // 找到根节点在中序遍历中的位置
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                index = i;
                break;
            }
        }
        // 构建左子树
        if (index > 0){
            root.left = buildTree(
                    Arrays.copyOfRange(preorder, 1, index + 1),
                    Arrays.copyOfRange(inorder, 0, index));
        }
        // 构建右子树
        if (index < inorder.length - 1){
            root.right = buildTree(
                    Arrays.copyOfRange(preorder, index + 1, preorder.length),
                    Arrays.copyOfRange(inorder, index + 1, inorder.length));
        }
        return root;
    }
}
