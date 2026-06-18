package com.June;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/06/11/09:00
 * @description:
 *  力扣3558. 给边赋权值的方案数Ⅰ
 */
public class day11 {
    static final int MOD = 1000000007;

    /**
     *  拆解题目：
     *  有N个节点的无向树，用edges[i] = [ai, bi]表示第i条边连接ai和bi两个节点。这里的ai和bi之间的权值可以为1或者2
     *  求深度最大，以及是奇数和的权总数的方案数量
     *  分析题目: 首先找到深度最大的节点(可能有多个)，然后返回遇到的奇数和权值的方案数量(答题思路)
     *  步骤：转换—>将求任一深度最大节点转为为根到这个节点路过的边，然后求出奇数和权值的方案数
     * @param edges
     * @return
     */
    private static int assignEdgeWeights(int[][] edges) {
        int n = edges.length+1;
        List<Integer>[] graph = new ArrayList[n+1];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for(int[] e: edges){
            int a = e[0];
            int b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        int maxDepth = dfs(1,0, graph);
        return (int)pow(2, maxDepth -1);
    }
    private static int dfs(int currentNode, int parent, List<Integer>[] graph){
        int maxDepth =0;
        for (int neighbor : graph[currentNode]){
            if (neighbor != parent){
                maxDepth = Math.max(maxDepth,dfs(neighbor, currentNode, graph)+1);
            }
        }
        return maxDepth;
    }
    private static long pow(int a, int b){
        long res = 1;
        while (b > 0){
            if ((b & 1) == 1){
                res = res * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(assignEdgeWeights(new int[][]{{1,2}}));//1
        System.out.println(assignEdgeWeights(new int[][]{{1,2},{1,3},{3,4},{3   ,5}}));//2
    }
}
