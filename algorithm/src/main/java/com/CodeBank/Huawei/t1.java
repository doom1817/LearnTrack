package com.CodeBank.Huawei;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/04/28/09:48
 * @description: 4-22 华为。
 * 题目：依赖关系有向图判环；无环则对每个被依赖包按最大版本号回填原始记录。
 */
public class t1 {
    public static void main(String[] args) {
        // 测试数据组 1：存在循环依赖 (A->B->C->A)
        List<String> case1 = Arrays.asList("A,B,1.0", "B,C,2.0", "C,A,1.5");

        // 测试数据组 2：无循环依赖，需要版本规整
        // 逻辑：A->B(1.0), C->B(2.5), A->D(1.0)
        // 预期：B的最大版本是2.5，A->B更新为2.5
        List<String> case2 = Arrays.asList("A,B,1.0", "C,B,2.5", "A,D,1.0");

        System.out.println("--- 测试结果 1 (预期 false) ---");
        resolveDependencies(case1);

        System.out.println("\n--- 测试结果 2 (预期更新版本) ---");
        resolveDependencies(case2);
    }

    /**
     * 依赖关系实体类，用于存储解析后的数据
     */
    static class Dependency {
        String a; // 依赖者
        String b; // 被依赖者
        String v; // 版本号

        public Dependency(String a, String b, String v) {
            this.a = a;
            this.b = b;
            this.v = v;
        }

        @Override
        public String toString() {
            return a + "," + b + "," + v;
        }
    }

    private static void resolveDependencies(List<String> dependencies) {
        List<Dependency> edges = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>(); // 邻接表 a -> [b]
        Map<String, Integer> inDegree = new HashMap<>();   // 入度表
        Map<String, String> maxTargetVersion = new HashMap<>(); // 被依赖者 b -> 最大版本
        //1.解析并构建建图
        for (String line : dependencies) {
            String[] parts = line.split(",");
            String a = parts[0];
            String b = parts[1];
            String v = parts[2];

            Dependency dep = new Dependency(a, b, v);
            edges.add(dep);

            //初始话图节点
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            inDegree.putIfAbsent(a, 0);
            inDegree.putIfAbsent(b, 0);

            //构建a-b
            graph.get(a).add(b);
            inDegree.put(b, inDegree.get(b) + 1);
            //记录被依赖者b的最大版本
            if (!maxTargetVersion.containsKey(b)) {
                maxTargetVersion.put(b, v);
            } else {
                if (compareVersion(v, maxTargetVersion.get(b)) > 0) {
                    maxTargetVersion.put(b, v);
                }
            }
        }
        //2.拓扑排序
        Queue<String> queue = new LinkedList<>();
        for (String node : graph.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            visited++;
            for (String next : graph.get(cur)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        //3.判断是否存在环
        if (visited != graph.size()) {
            System.out.println("false");
            return;
        }
        //4.回填版本号
        List<String> result = new ArrayList<>();
        for (Dependency edge : edges) {
            String finalVersion = maxTargetVersion.get(edge.b);
            result.add(edge.a + "," + edge.b + "," + finalVersion);
        }
        for (String line : result) {
            System.out.println(line);
        }
    }

    /**
     * 辅助方法：比较版本号大小
     * 返回 1: v1 > v2
     * 返回 -1: v1 < v2
     * 返回 0: v1 == v2
     */
    private static int compareVersion(String v1, String v2) {
        String[] arr1 = v1.split("\\.");
        String[] arr2 = v2.split("\\.");
        int len = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < len; i++) {
            int num1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            int num2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        return 0;
    }

}
