package com.interview.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
/**
 * @author: doom
 * @date: 2026/06/16/09:23
 * @description:
 * 这段代码对比测试 HashMap 和 Hashtable 的差异：
 * 功能差异：演示 null 键值支持（HashMap 允许，Hashtable 抛异常）、默认容量和扩容策略不同
 * 性能测试：在单线程环境下对 100 万数据进行 put/get 操作，统计耗时并计算性能倍数差异
 */
public class HashTableVsHashMap {
    // 测试数据量
    private static final int SIZE = 1_000_000;

    public static void main(String[] args) {
        System.out.println("========== 1. 功能差异演示 ==========");
        demonstrateFunctionalDifferences();

        System.out.println("\n========== 2. 性能基准测试 ==========");
        benchmarkPerformance();
    }

    /**
     * 演示两者的关键功能差异
     */
    private static void demonstrateFunctionalDifferences() {
        Map<String, String> hashMap = new HashMap<>();
        Hashtable<String, String> hashtable = new Hashtable<>();

        // ✅ 差异1: null 键值支持
        try {
            hashMap.put(null, "nullKey");
            hashMap.put("key", null);
            System.out.println("[HashMap]  允许 null key 和 null value ✔");
        } catch (Exception e) {
            System.out.println("[HashMap]  不允许 null ✘");
        }

        try {
            hashtable.put(null, "nullKey");
            System.out.println("[Hashtable] 允许 null key ✔");
        } catch (NullPointerException e) {
            System.out.println("[Hashtable] 不允许 null key ✘ (抛出 NullPointerException)");
        }

        try {
            hashtable.put("key", null);
            System.out.println("[Hashtable] 允许 null value ✔");
        } catch (NullPointerException e) {
            System.out.println("[Hashtable] 不允许 null value ✘ (抛出 NullPointerException)");
        }

        // ✅ 差异2: 初始容量与扩容策略
        System.out.println("\n[HashMap]  默认容量=16, 负载因子=0.75, 扩容为2倍");
        System.out.println("[Hashtable] 默认容量=11, 负载因子=0.75, 扩容为2n+1");

        // ✅ 差异3: 迭代器 vs 枚举器
        System.out.println("\n[HashMap]  使用 Iterator (fail-fast)");
        System.out.println("[Hashtable] 同时支持 Iterator(fail-fast) 和 Enumeration(非fail-fast)");
    }

    /**
     * 单线程下的读写性能基准测试
     */
    private static void benchmarkPerformance() {
        // --- HashMap 写入 ---
        Map<Integer, Integer> hashMap = new HashMap<>(SIZE);
        long start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            hashMap.put(i, i);
        }
        long hashMapPutTime = System.nanoTime() - start;

        // --- Hashtable 写入 ---
        Hashtable<Integer, Integer> hashtable = new Hashtable<>(SIZE);
        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            hashtable.put(i, i);
        }
        long hashtablePutTime = System.nanoTime() - start;

        // --- HashMap 读取 ---
        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            hashMap.get(i);
        }
        long hashMapGetTime = System.nanoTime() - start;

        // --- Hashtable 读取 ---
        start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            hashtable.get(i);
        }
        long hashtableGetTime = System.nanoTime() - start;

        // --- 输出结果 ---
        System.out.printf("%-12s | %-12s | %-12s%n", "操作", "HashMap", "Hashtable");
        System.out.println("-".repeat(42));
        System.out.printf("%-12s | %,8d ms  | %,8d ms%n",
                "put(100万)", hashMapPutTime / 1_000_000, hashtablePutTime / 1_000_000);
        System.out.printf("%-12s | %,8d ms  | %,8d ms%n",
                "get(100万)", hashMapGetTime / 1_000_000, hashtableGetTime / 1_000_000);
        System.out.printf("%n⚡ HashMap 写入快约 %.1f 倍, 读取快约 %.1f 倍%n",
                (double) hashtablePutTime / hashMapPutTime,
                (double) hashtableGetTime / hashMapGetTime);
    }
}
