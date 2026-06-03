package com.June;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/06/02/09:14
 * @description: 力扣3633. 最早完成陆地和水上游乐设施的时间 I
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(earliestFinishTime(new int[]{2, 8}, new int[]{4, 1}, new int[]{0}, new int[]{3})); // 9
    }

    /**
     * 分类讨论
     * 1.起点应该早
     * 2.这一次startTime+duration应该最好大于等于下一个开放时间
     * 3.各自体验一个就行了
     *
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    private static int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landWater = solve(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterLand = solve(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(landWater, waterLand);
    }
    private static int solve(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration){
        // 1. 找到所有陆地设施中最早的结束时间
        int minFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++){
            minFinish =  Math.min(minFinish,landDuration[i]+landStartTime[i]);
        }
        // 2. 对于每个水上设施，计算从 minFinish 开始（若水开放更晚则等待）的完成时间，取最小值
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++){
            res  =  Math.min(res,Math.max(waterStartTime[i],minFinish)+waterDuration[i]);
        }
        return res;
    }
}
