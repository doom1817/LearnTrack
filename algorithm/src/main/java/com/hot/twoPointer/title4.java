package com.hot.twoPointer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/19:33
 * @Description:
 * 力扣42. 接雨水
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{4,2,0,3,2,5}));
    }
    public static int trap(int[] height) {
        int heightLength = height.length;
        if (heightLength < 2) return 0;
        int left = 0; // 左指针
        int right = heightLength - 1; // 右指针
        int leftMax = height[left]; //记录左边的最大值
        int rightMax = height[right]; //记录右边的最大值
        int res = 0;
        while (left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 移动指针 ，更新res中保存的雨水高度
            if (leftMax < rightMax){
                res += leftMax - height[left++]; // 当前位置的雨水高度为leftMax减去当前位置的高度
            }else {
                res += rightMax - height[right--]; // 当前位置的雨水高度为rightMax减去当前位置的高度
            }
        }
        return res;
    }
}
