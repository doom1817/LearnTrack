package com.DP;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/11/12:52
 * @Description:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {
    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        serialize(this, sb);
        return sb.toString();
    }

    // 序列化方法：递归构建字符串
    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null");
            return;
        }
        sb.append("[").append(node.val).append(",");
        serialize(node.left, sb);
        sb.append(",");
        serialize(node.right, sb);
        sb.append("]");
    }
}
