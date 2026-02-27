package com.hot.Substring;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: doom
 * @date: 2026/02/23/19:54
 * @description:
 * 力扣239. 滑动窗口最大值
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{7, 2, 4}, 2)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0|| k == 0) return new int[0];
        int[] ans = new int[nums.length - k + 1];
        Deque< Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++){
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1){
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }
}
