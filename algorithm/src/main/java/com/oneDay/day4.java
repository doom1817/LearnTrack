package com.oneDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/22/15:06
 * @Description:
 *  力扣868. 二进制间距
 */
public class day4 {
    public static void main(String[] args) {
        System.out.println(binaryGap(22));
        System.out.println(binaryGap(8));
        System.out.println(binaryGap(5));
    }
    /**
     * 计算给定正整数的二进制表示中，两个相邻的 '1' 之间的最大距离。
     *
     * 解题思路：
     * 1. 将输入整数转换为二进制字符串。
     * 2. 使用单次遍历的方式扫描二进制字符串，记录每个 '1' 的位置。
     * 3. 维护一个变量 `lastOneIndex` 记录上一个 '1' 的索引位置。
     * 4. 每次遇到新的 '1' 时，计算当前索引与上一个 '1' 的距离，并更新最大距离。
     * 5. 返回最终的最大距离。
     *
     * 算法思想：线性扫描 + 状态记录，时间复杂度为 O(n)，其中 n 为二进制字符串的长度。
     *
     * @param n 输入的正整数
     * @return 返回两个相邻 '1' 之间的最大距离；如果不存在两个 '1'，则返回 0
     */
    public static int binaryGap(int n) {
        String binaryStr = Integer.toBinaryString(n);
        int maxDistance = 0;
        int lastOneIndex = -1;
        for (int currentIndex = 0; currentIndex < binaryStr.length(); currentIndex++) {
            if (binaryStr.charAt(currentIndex) == '1') {
                if (lastOneIndex != -1) {
                    maxDistance = Math.max(maxDistance, currentIndex - lastOneIndex);
                }
                lastOneIndex = currentIndex;
            }
        }
        return maxDistance;
    }
    /**
     * 优化方法：
     * 1. 创建一个列表 `onesPositions` 用于存储二进制字符串中 '1' 的位置。
     * 2. 使用位运算来遍历二进制字符串，将每个 '1' 的位置添加到 `onesPositions` 列表中。
     * 3. 遍历 `onesPositions` 列表，计算相邻 '1' 之间的距离，并更新最大距离。
     * 4. 返回最终的最大距离。
     * 优化思路：
     * 1. 创建一个列表 `onesPositions` 用于存储二
     * @param n 输入的
     * @return
     */
    public static int binaryGap1(int n) {
        List<Integer> onesPositions = new ArrayList<>();
        int position = 0;
        while (n > 0){
            if ((n & 1) == 1){
                onesPositions.add(position);
            }
            n>>=1;
            position++;
        }
        int maxDistance = 0;
        for (int i = 0; i < onesPositions.size()-1; i++) {
            maxDistance = Math.max(maxDistance, onesPositions.get(i+1) - onesPositions.get(i));
        }
        return maxDistance;
    }
}
