package com.oneDay;

import com.System.function;

import java.util.*;

/**
 * @author: doom
 * @date: 2026/02/25/20:52
 * @description:
 * 力扣1356. 根据数字二进制中1的数目排序
 */
public class day7 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortByBits(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(Arrays.toString(sortByBits(new int[]{1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1})));
        System.out.println(Arrays.toString(sortByBits(new int[]{10000, 10000})));
    }
    /**
     * 对整数数组按照其二进制表示中1的个数进行排序。
     * 如果两个数的二进制中1的个数相同，则按数值大小排序。
     *
     * 算法思路：
     * 1. 编码阶段：将每个数字的二进制1的个数存储在高16位，原数字存储在低16位
     * 2. 排序阶段：利用Java内置排序对编码后的数组进行排序
     * 3. 解码阶段：通过位运算提取低16位的原始数值
     *
     * 运用算法：
     * - 位运算：使用左移(<<)和按位或(|)进行编码，使用按位与(&)进行解码
     * - 排序算法：依赖Arrays.sort()的双轴快排实现(O(n log n)平均时间复杂度)
     * - 计数算法：Integer.bitCount()高效计算二进制中1的个数
     *
     * @param arr 输入的整数数组
     * @return 排序后的整数数组
     */
    public static int[] sortByBits(int[] arr) {
        int n = arr.length;
        int[] encoded = new int[n];

        // 编码阶段：将1的个数放在高位，原数字放在低位
        for (int i = 0; i < n; i++){
            int bits = Integer.bitCount(arr[i]);
            encoded[i]=(bits << 16)| arr[i];
        }

        // 排序阶段：Java内置排序会自动按编码值升序排列
        Arrays.sort(encoded);

        // 解码阶段：提取低16位恢复原始数值
        for (int i = 0; i < n; i++){
            arr[i] = encoded[i] & 0xFFFF;
        }

        return arr;
    }

}
