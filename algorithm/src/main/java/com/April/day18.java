package com.April;

/**
 * @author: doom
 * @date: 2026/04/18/13:39
 * @description:
 *  力扣3783.整数的镜像距离
 */
public class day18 {
    public static void main(String[] args) {
        System.out.println(mirrorDistance(25));//27
        System.out.println(mirrorDistance(10));//9
        System.out.println(mirrorDistance(7));//0
    }
    public static int mirrorDistance(int n) {
        return Math.abs(Integer.parseInt(new StringBuilder(n+"").reverse().toString())-n);
    }
}
