package com.hot.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author: doom
 * @date: 2026/04/23/09:35
 * @description:
 *  力扣215. 数组中的第K个最大元素
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));//5
    }
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num:nums){
            minHeap.offer(num);
            if (minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
