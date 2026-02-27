package com.hot.slidingWindow;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/22/19:48
 * @Description:
 * 力扣3206。交替组|
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(numberOfAlternatingGroups1(new int[]{1,1,1}));
        System.out.println(numberOfAlternatingGroups1(new int[]{0,1,0,0,0,1}));

    }

    /**
     * 核心思想是枚举中心点
     * @param colors
     * @return
     */
    public static int numberOfAlternatingGroups(int[] colors) {
        int ans = 0;
        int n = colors.length;
        for (int i = 0; i < n; i++){
            /*
            1. colors[(n+i-1)%n] != colors[i] 表示当前位置和前一个位置的颜色不同
            2. colors[i] != colors[(i+1)%n] 表示当前位置和下一个位置的颜色不同
             */
            if (colors[(n+i-1)%n] != colors[i] && colors[i] != colors[(i+1)%n]){
                ans++;
            }
        }
        return ans;
    }
    public static int numberOfAlternatingGroups1(int[] colors) {
        int n = colors.length;
        if (n < 3)return 0;
        int count = 0;
        for (int i = 0; i < n; i++){
            if (colors[i] != colors[(i+1)%n]&&
            colors[(i+1)%n] != colors[(i+2)%n]){
                count++;
            }
        }
        return count;
    }
}
