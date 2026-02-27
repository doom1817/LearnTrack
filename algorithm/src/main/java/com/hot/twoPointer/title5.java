package com.hot.twoPointer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/20:15
 * @Description:
 * 力扣27. 移除元素
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{3,2,2,3}, 3));
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            // 判断当前元素是否等于val
            if (nums[i] != val){
                // 不等于val，则将当前元素赋给count位置
                nums[count++] = nums[i];
            }
        }
        return count;
    }
}
