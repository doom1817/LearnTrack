package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/16/12:45
 * @Description:
 * 力扣983. 最低票价
 */
public class title5 {
    public static void main(String[] args) {
        System.out.println(new title5().minTickets(new int[]{1,4,5,6,7,8,20}, new int[]{2,7,15}));
    }

    public int minTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        boolean[] isTravelDay = new boolean[lastDay + 1];
        for (int day : days) {
            isTravelDay[day] = true;
        }
        int[] dp = new int[lastDay + 1];
        dp[0]=0;
        for (int i = 1; i <= lastDay; i++){
            if (!isTravelDay[i]){
                dp[i] = dp[i - 1];
            }
            else {
                dp[i] = Math.min(dp[i - 1] + costs[0],
                        Math.min(dp[Math.max(i - 7, 0)] + costs[1],
                                dp[Math.max(i - 30, 0)] + costs[2]));
            }
        }
        return dp[lastDay];
    }
}
