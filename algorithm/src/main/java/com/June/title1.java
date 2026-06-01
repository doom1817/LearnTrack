package com.June;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/06/01/09:13
 * @description:
 *  力扣2144. 打折购买糖果的最小开销
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(minimumCost(new int[]{1,2,3}));//5
    }
    private static int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int sum = 0;
        //从最贵的开始->每次处理三个糖果
        for (int i = cost.length-1; i >= 0; i-=3) {
            sum += cost[i]; //最贵的
            if(i-1 >= 0) sum += cost[i-1];//第二贵的
            //剩下的就是送的
        }
        return sum;
    }
}
