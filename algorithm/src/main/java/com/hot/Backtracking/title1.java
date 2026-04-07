package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/03/18/10:26
 * @description:
 * 力扣46.全排列
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute(new int[]{0,1}));
        System.out.println(permute(new int[]{1}));
    }
    // 定义一个全局变量来存放所有结果，这样在递归的各个层级都能方便地访问
    private static List<List<Integer>> result;
    private static List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            result.add(new ArrayList<>());
            return result;
        }
        backtrack(nums, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }
    private static void backtrack(int[] nums, List<Integer> path, boolean[] used){
        //终止条件 ，当path的长度等于nums的长度，则返回path
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        //循环遍历nums
        for (int i = 0; i < nums.length; i++) {
            //剪枝，如果使用过就进行skip
            if(used[i]){
                continue;
            }
            //添加当前元素，标记为使用过
            path.add(nums[i]);
            used[i] = true;
            backtrack(nums, path, used);
            //回溯，撤销选择，将当前元素从path中删除，并标记为未使用
            path.remove(path.size()-1);
            used[i] = false;
        }
    }
}
