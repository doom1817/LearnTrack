package com.hot.Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * @author: doom
 * @date: 2026/04/21/10:24
 * @description:
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); //[1,1,4,2,1,1,0,0]
    }
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] answers = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++){
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int prevIndex = stack.pop();
                answers[prevIndex] = i - prevIndex;
            }
            stack.push(i);

        }
        return answers;
    }
    //ArrayDeque的特性：首尾均可高效操作的循环数组设计
    private static int[] dailyTemperatures1(int[] temperatures) {
        Deque<Integer> queue = new ArrayDeque<>();
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int t = temperatures[i];
            while (!queue.isEmpty() && temperatures[queue.peek()] <= t){
                queue.pop();
            }
            if (!queue.isEmpty()){
                ans[i] = queue.peek() - i;
            }
            queue.push(i);
        }
        return ans;
    }
}
