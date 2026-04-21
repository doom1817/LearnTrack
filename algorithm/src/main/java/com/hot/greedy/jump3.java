package com.hot.greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: doom
 * @date: 2026/04/24/09:47
 * @description:
 *  力扣1306. 跳跃游戏 III
 */
public class jump3 {
    public static void main(String[] args) {
        System.out.println(canReach(new int[]{4,2,3,0,3,1,2}, 5));// true
    }
    private static boolean canReach(int[] arr, int start) {
//            int[] visited = new int[arr.length];
//            return dfs(arr, start, visited);
        boolean[] visited = new boolean[arr.length];
        return bfs(arr, start, visited);
    }
    private static boolean dfs(int[] arr, int start, int[] visited){
        if (start < 0 || start >= arr.length || visited[start] == 1){
            return false;
        }
        if (arr[start] == 0){
            return true;
        }
        visited[start] = 1;
        return dfs(arr, start + arr[start], visited) || dfs(arr, start - arr[start], visited);
    }

    private static boolean bfs(int[] arr, int start, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        // 广度优先搜索 初始化
        queue.offer(start);
        visited[start] = true;
        // 广度优先搜索 层次遍历
        while (!queue.isEmpty()){
            int current = queue.poll();
            //终止条件 找到0
            if (arr[current] == 0){
                return true;
            }
            // 向前跳，向后跳
            int nextForward = current + arr[current];
            int nextBackward = current - arr[current];

            //如果向前跳的位置合法且未访问，加入队列
            if (nextForward<arr.length && !visited[nextForward]){
                queue.offer(nextForward);
                visited[nextForward] = true;
            }
            //如果向后跳的位置合法且未访问，加入队列
            if (nextBackward >= 0 && !visited[nextBackward]){
                queue.offer(nextBackward);
                visited[nextBackward] = true;
            }
        }
        return false;
    }
}
