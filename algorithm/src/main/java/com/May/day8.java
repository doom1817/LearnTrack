package com.May;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/05/08/09:06
 * @description:
 *  力扣3629.通过质数传送到达终点的最少跳跃次数
 */
public class day8 {
    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{1,2,4,6}));//2
        System.out.println(minJumps(new int[]{2,3,4,7,9}));//2
    }
    private static int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        Set<Integer> usedPrimes = new HashSet<>();
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        while (!q.isEmpty()){
            int curr = q.poll();
            //到达终点
            if (curr == n - 1){
                return dist[curr];
            }
            //1. curr+1
            if (curr + 1 < n && !visited[curr + 1]){
                visited[curr + 1] = true;
                dist[curr + 1] = dist[curr] + 1;
                q.offer(curr + 1);
            }
            //2. curr-1
            if (curr - 1 >= 0 && !visited[curr - 1]){
                visited[curr - 1] = true;
                dist[curr - 1] = dist[curr] + 1;
                q.offer(curr - 1);
            }
            //3. 质数传送
            int val = nums[curr];
            if (isPrime( val) && usedPrimes.add(val)){
                for (int j = 0; j < n; j++){
                    if (!visited[j] && nums[j] % val == 0){
                        visited[j] = true;
                        dist[j] = dist[curr] + 1;
                        q.offer(j);
                    }
                }
            }
        }
        return -1;
    }
    private static boolean isPrime(int x){
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2){
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }
}
