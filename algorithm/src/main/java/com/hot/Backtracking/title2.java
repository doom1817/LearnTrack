package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/03/18/10:59
 * @description:
 * 力扣78. 子集
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(subsets(new int[]{0}));
    }
    private static List<List<Integer>> ans;
    private static List<List<Integer>> subsets(int[] nums){
        ans = new ArrayList<>();;
        if (nums == null || nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        backtrack(nums,0, new ArrayList<>());
        return ans;
    }
    private static void backtrack(int[] nums, int start,List<Integer> path){
        //终止条件
        ans.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++){
            path.add(nums[i]);
            backtrack(nums,i+1,path);
            path.remove(path.size()-1);
        }
    }
}
