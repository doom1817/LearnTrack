package com.hot.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: doom
 * @date: 2026/04/21/10:39
 * @description:
 *  力扣84. 柱状图中最大的矩形
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3})); //10
    }
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        //遍历所有柱子，维护单调递增栈
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        // 栈中剩余的柱子，计算其面积
        while (!stack.isEmpty()){
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
    }
}
