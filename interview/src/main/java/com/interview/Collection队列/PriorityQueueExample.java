package com.interview.Collection队列;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author: doom
 * @date: 2026/06/08/19:48
 * @description:
 */
public class PriorityQueueExample {

    public static void main(String[] args) {
        // 创建一个 Integer 的优先队列，默认自然排序（最小堆）
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 添加元素
        System.out.println("Adding elements: 30, 10, 50, 20, 40");
        pq.add(30);
        pq.add(10);
        pq.add(50);
        pq.add(20);
        pq.add(40);

        System.out.println("Size after adding: " + pq.size());

        // 注意：直接打印队列（使用迭代器）并不保证显示排序后的结果
        // 因为迭代器通常按内部存储顺序（堆的数组表示）遍历
        System.out.println("Internal order (via iterator, not sorted): " + Arrays.toString(pq.toArray()));

        System.out.println("\nPolling elements (should be in sorted order due to priority):");
        while (!pq.isEmpty()) {
            int highestPriorityElement = pq.poll(); // 移除并返回优先级最高的元素
            System.out.println("Polled: " + highestPriorityElement);
        }

        System.out.println("\n--- Using Custom Comparator (Reverse Order - Max Heap) ---");

        // 创建一个使用自定义比较器的优先队列（最大堆）
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        System.out.println("Adding elements: 30, 10, 50, 20, 40");
        maxPQ.add(30);
        maxPQ.add(10);
        maxPQ.add(50);
        maxPQ.add(20);
        maxPQ.add(40);

        System.out.println("Internal order (via iterator, not sorted according to custom rule): " + Arrays.toString(maxPQ.toArray()));

        System.out.println("Polling elements (should be in reverse sorted order):");
        while (!maxPQ.isEmpty()) {
            int highestPriorityElement = maxPQ.poll(); // 移除并返回优先级最高的元素 (最大的)
            System.out.println("Polled: " + highestPriorityElement);
        }
    }
}
