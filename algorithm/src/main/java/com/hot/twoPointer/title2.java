package com.hot.twoPointer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/19:07
 * @Description:
 * 力扣11. 盛最多水的容器
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(maxArea(new int[]{1,1}));
    }
    /**
     * 解题思路：
     * 1. 创建两个指针，分别指向数组的左右边界
     * 2. 移动两个指针，直到两个指针相遇
     * 3. 计算两个指针之间的面积，并更新最大面积
     * 4. 根据两个指针之间的高度，移动两个指针
     * 5. 返回最大面积
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int heightLength = height.length;
        int left = 0;
        int right = heightLength - 1;
        int maxArea = Integer.MIN_VALUE;

        while (left < right) {
            // 计算当前容器的最小高度和宽度
            int minHeight = Math.min(height[left], height[right]);
            int width = right - left;
            int currentArea = minHeight * width;

            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短边的指针以尝试寻找更大面积
            if (height[left] < height[right]) {
                left++; // 向右移动左指针
            } else {
                right--; // 向左移动右指针
            }
        }

        return maxArea;
    }
}
