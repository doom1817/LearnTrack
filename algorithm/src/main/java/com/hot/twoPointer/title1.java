package com.hot.twoPointer;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/18:30
 * @Description:
 * 力扣283. 移动零
 */
public class title1 {
    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});
        moveZeroes(new int[]{0});
    }

    /**
     * 双指针-快慢指针
     * i：慢指针，这个指针负责遍历整个数组，寻找非零元素。
     * j：快指针。这个指针指向下一个非零元素应该被放置的位置。它的值恰好等于已经找到的非零元素的个数。
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int j =0; //1.负责标记新数组（非零部分）的边界。
        for(int i=0 ;i<nums.length;i++){
            // 找到非0的元素，交换位置
            if(nums[i]!=0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;// 3. 更新慢指针，指向下一个待填充位置 扩大边界
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
