package com.April;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/01/22:50
 * @description:
 *  力扣 2751. 机器人碰撞
 */
public class day1 {
    public static void main(String[] args) {
        System.out.println(survivedRobotsHealths(new int[]{5,4,3,2,1},new int[]{2,17,9,15,10}, "RRRRR"));//[2,17,9,15,10]
        System.out.println(survivedRobotsHealths(new int[]{3,5,2,6},new int[]{10,10,15,12}, "RLRL")); //[14]
    }
    private static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        // 创建一个下标数组，对下标数组排序，这样不会打乱输入顺序
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, Comparator.comparingInt(i -> positions[i]));
        int[] st = new int[n];
        int top = -1;
        for (int i: index){
            if (directions.charAt(i) == 'R'){ // 机器人向右移动
                st[++top] = i;
                continue;
            }
            while (top>=0){
                int j = st[top];
                if (healths[j]>healths[i]){
                    healths[i] = 0;
                    healths[j]--;
                    break;
                }
                if (healths[j]==healths[i]){
                    healths[i] = 0;
                    healths[j] = 0;
                    top--;
                    break;
                }
                healths[i]--;
                healths[j]=0;
                top--;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int h: healths){
            if (h>0){
                ans.add(h);
            }
        }
        return ans;
    }
}
