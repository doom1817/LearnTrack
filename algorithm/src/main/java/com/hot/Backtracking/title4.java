package com.hot.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/14/15:53
 * @description:
 *  力扣39.组合总和
 */
public class title4 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7)); //[[2,2,3],[7]]
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0){
            return res;
        }
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    private static void backtracking(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backtracking(candidates, target - candidates[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }



}
