package com.hot.binaryTree;

/**
 * @author: doom
 * @date: 2026/03/04/22:33
 * @description:
 */
public class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;
    TreeNode(){};
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        TreeNode cur = this;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.right != null){
                sb.append(",");
            }
            cur = cur.right;
        }
        sb.append("]");
        return super.toString();
    }
}

