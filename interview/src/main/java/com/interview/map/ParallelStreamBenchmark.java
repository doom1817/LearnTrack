package com.interview.map;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;
/**
 * @author: doom
 * @date: 2026/06/16/09:57
 * @description:
 */
public class ParallelStreamBenchmark {
    public static void main(String[] args) {
        int SIZE = 1_000_000;
        Map<Integer, Integer> map = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) map.put(i, i);

        // 场景A：轻量级操作（仅累加）
        long t1 = System.currentTimeMillis();
        long sum1 = map.values().stream().mapToLong(Integer::longValue).sum();
        System.out.println("串行流(轻量): " + (System.currentTimeMillis() - t1) + " ms");

        long t2 = System.currentTimeMillis();
        long sum2 = map.values().parallelStream().mapToLong(Integer::longValue).sum();
        System.out.println("并行流(轻量): " + (System.currentTimeMillis() - t2) + " ms");
        // 💡 结果：并行流可能比串行流更慢，或提升不明显

        // 场景B：重量级操作（模拟复杂计算，每个元素耗时 1 毫秒）
        long t3 = System.currentTimeMillis();
        map.values().stream().forEach(ParallelStreamBenchmark::heavyCompute);
        System.out.println("串行流(重量): " + (System.currentTimeMillis() - t3) + " ms");

        long t4 = System.currentTimeMillis();
        map.values().parallelStream().forEach(ParallelStreamBenchmark::heavyCompute);
        System.out.println("并行流(重量): " + (System.currentTimeMillis() - t4) + " ms");
        // 💡 结果：并行流速度呈倍数级提升（取决于 CPU 核心数）
    }

    // 模拟 CPU 密集型计算
    private static void heavyCompute(int n) {
        long sum = 0;
        for (long i = 0; i < 100000; i++) sum += i;
    }
}
