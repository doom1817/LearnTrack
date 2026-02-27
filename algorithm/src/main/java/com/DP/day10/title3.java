package com.DP.day10;

import com.DP.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/11/12:53
 * @Description:
 * 力扣95. 不同的二叉搜索树 II
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(generateTrees(3));
        System.out.println(generateTrees(1));
    }
    private static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++){
            // 递归生成左子树（范围[start, i-1]）
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 递归生成右子树（范围[i+1, end]）
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            for (TreeNode leftTree : leftTrees){
                for (TreeNode rightTree : rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
