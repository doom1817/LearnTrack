package com.CodeBank.week;

/**
 * @author: doom
 * @date: 2026/05/24/21:35
 * @description:
 */
public class q3 {
    public static int minOperations(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int incBreaks = 0; //递增断点
        int decBreaks = 0;//递减断点
        int zeroIndex = -1; //0的索引
        for (int i = 0; i < n; i++){
            if (nums[i] == 0){
                zeroIndex = i;
            }
            int next = nums[(i+1)%n];//实现首尾相连的环形数组
            if (nums[i] > next){
                incBreaks++;
            }else if (nums[i] < next){
                decBreaks++;
            }
        }
        //循环递增
        if (incBreaks == 1){
            if (zeroIndex == 0){
                return 0; //终止条件
            }
            return Math.min(zeroIndex, n-zeroIndex+2);//这里+2是两次翻转
        }
        //循环递减
        if (decBreaks == 1){
            return Math.min(n-zeroIndex, (zeroIndex+1)%n+1);
            // n-zeroIndex是1 + (n - 1 - zeroIndex)。先反转再左旋
            // (zeroIndex+1)%n+1是(zeroIndex + 1)%n +1。先左旋再反转
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {0,2,1};
        System.out.println(minOperations(nums));
    }
}
