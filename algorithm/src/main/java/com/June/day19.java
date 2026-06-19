package com.June;

/**
 * @author: doom
 * @date: 2026/06/19/12:45
 * @description:
 *  力扣1732. 找到最高海拔
 */
public class day19 {
    public static void main(String[] args) {
//        System.out.println(largestAltitude(new int[]{-5,1,5,0,-7}));
        System.out.println(largestAltitude(new int[]{-4,-3,-2,-1,4,3,2}));
    }
    private static int largestAltitude(int[] gain) {
        int temp = 0; //0是起点
        int highest = 0;
        for (int j : gain) {
            temp += j;
            if (temp > highest) {
                highest = temp;
            }
        }
        return highest;
    }
}
