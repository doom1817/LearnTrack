package com.May;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/31/13:51
 * @description:
 * 力扣2126.摧毁小行星
 */
public class day31 {
    public static void main(String[] args) {
        System.out.println(asteroidsDestroyed(10, new int[]{3, 9, 19, 5, 21}));//true
        System.out.println(asteroidsDestroyed(5, new int[]{4, 9, 23, 4}));//false
    }

    /**
     *  1.第一次碰撞就要求至少大于等于最小的小行星
     *  2.后面每次碰撞都会大于等于下一个小行星
     * @param mass
     * @param asteroids
     * @return
     */
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        if (asteroids.length == 0) return true;
        //如果第一次连最小的小行星都比mass小，则无法摧毁
        Arrays.sort(asteroids);
        if (mass < asteroids[0]){
            return false;
        }
        for (int asteroid : asteroids) {
            if (mass < asteroid) {
                return false;
            }
            mass += asteroid;
        }
        return true;
    }
}
