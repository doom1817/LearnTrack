package com.April;

/**
 * @author: doom
 * @date: 2026/04/20/09:20
 * @description:
 *  力扣2078.两栋颜色不同且距离最远的房子
 */
public class day20 {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{1,1,1,6,1,1,1})); //3
    }

    public static int maxDistance(int[] colors) {
        int maxDiff = 0;
        int n = colors.length;
        for (int i=0;i<n;i++){
            if (colors[i] != colors[0]) {
                maxDiff = Math.max(maxDiff, i);
            }
            if (colors[i] != colors[n-1]){
                maxDiff = Math.max(maxDiff, n-1-i);
            }
        }
        return maxDiff;
    }
}
