package com.System;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/21/19:01
 * @Description:
 * 记录System解题使用到的方法
 */
public class function {
    /**
     * 数组拷贝
     *System.arraycopy(arr, 0, arr1, 0, arr.length);
     * 等效于 for(int i=0;i<arr.length;i++){arr1[i] = arr[i];}
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr){
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1, 0, arr.length);
        return arr1;
    }

}
