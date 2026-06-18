package com.June;

/**
 * @author: doom
 * @date: 2026/06/18/09:05
 * @description:
 */
public class day18 {
    public static double angleClock(int hour, int minutes) {
            hour%=12;
        // 时针角度：每小时 30°，每分钟 0.5°
        double hourAngle = 30 * hour + 0.5 * minutes;
        // 分针角度：每分钟 6°
        double minuteAngle = 6 * minutes;
        //计算夹角
        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360 - diff);
    }

    public static void main(String[] args) {
        System.out.println(angleClock(12, 30));//165
    }
}
