package com.May;

/**
 * @author: doom
 * @date: 2026/05/24/21:02
 * @description:
 *  力扣1340. 跳跃游戏 V
 */
public class day24 {
    public static void main(String[] args) {
        int[] arr = {6,4,14,6,8,13,9,7,10,6,12};
        System.out.println(maxJump(arr,2));
    }
    private static int maxJump(int[] arr,int d){
        int n = arr.length;
        int[] memo = new int[n]; // 记录当前位置的跳数
        int maxResult = 0;
        for (int i = 0; i < n; i++){
            maxResult = Math.max(maxResult,dfs(i,d,arr,memo));
        }
        return maxResult;
    }
    private static int dfs(int i,int d,int[] arr,int[] memo){
        if (memo[i] != 0){
            return memo[i];
        }
        int maxJump = 1;
        for (int j = i + 1; j < Math.min(i + d + 1, arr.length-1); j++){
            if (arr[j]>= arr[i]){
                break;
            }
            maxJump = Math.max(maxJump,dfs(j,d,arr,memo) + 1);
        }
        for (int j = i - 1; j > Math.max(i - d, 0); j--){
            if (arr[j] >= arr[i]){
                break;
            }
            maxJump = Math.max(maxJump,dfs(j,d,arr,memo) + 1);
        }
        memo[i] = maxJump;
        return maxJump;
    }
}
