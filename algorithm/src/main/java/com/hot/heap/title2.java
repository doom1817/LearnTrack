package com.hot.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author: doom
 * @date: 2026/04/23/11:41
 * @description: 力扣347. 前 K 个高频元素
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
    }

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (minHeap.size() < k) {
                // 堆没满，直接进
                minHeap.offer(new int[]{num, count});
            } else {
                // 堆满了，比较当前元素频率与堆顶元素频率
                // 如果当前元素频率 > 堆顶频率，说明堆顶不配留在 Top K 里，将其移除
                if (count > minHeap.peek()[1]) {
                    minHeap.poll();
                    minHeap.offer(new int[]{num, count});
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }
        return result;
    }
}