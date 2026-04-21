package com.hot.greedy;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/04/24/10:05
 * @description:
 *  力扣1345. Jump Game IV
 */
public class jump4 {
    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{100,-23,-23,404,100,23,23,23,23,-23,23}));//3
    }

    public static int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        // 记录相同数字的索引
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        // 广度优先搜索 初始化
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int step = 0;

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++){
                int curr = queue.poll();
                //终止条件
                if (curr == n-1) {return step;}

                List<Integer> neighbors = new ArrayList<>();
                //i+1
                if (curr+1<n) neighbors.add(curr+1);
                //i-1
                if (curr-1>=0) neighbors.add(curr-1);
                //同值跳跃
                if (map.containsKey(arr[curr])){
                    neighbors.addAll(map.get(arr[curr]));
                    map.remove(arr[curr]);
                }
                for (int neighbor : neighbors){
                    if (!visited[neighbor]){
                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
