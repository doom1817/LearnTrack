package com.hot.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/28/11:01
 * @description:
 *  力扣118. 杨辉三角
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }
    /**
     * 生成杨辉三角的前numRows行
     * 杨辉三角的性质：每个数等于它左上方和右上方的数的和
     * 每行的第一个和最后一个数都是1，中间的数由上一行相邻两个数相加得到
     *
     * @param numRows 要生成的杨辉三角的行数
     * @return 包含杨辉三角前numRows行的二维列表
     */
    private static List<List<Integer>> generate(int numRows){
        // 边界条件处理
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        // 创建一个二维列表，用于存储杨辉三角
        List<List<Integer>> res = new ArrayList<>();
        //外层循环，迭代每一行
        for (int i = 0; i < numRows; i++) {
            // 创建当前行的列表，初始容量设置为i+1（因为第i行有i+1个元素）
            List<Integer> cur = new ArrayList<>(i + 1);
            //内层循环，迭代当前行的元素
            for (int j = 0; j <= i; j++) {
                // 判断当前元素是否为第一个或最后一个，如果是，则直接添加1
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    // 否则，当前位置的值等于上一行相邻两个元素的和
                    // res.get(i-1).get(j-1)是上一行的左上方元素
                    // res.get(i-1).get(j)是上一行的右上方元素
                    cur.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            // 将当前行添加到结果列表中
            res.add(cur);
        }
        return res;
    }

}
