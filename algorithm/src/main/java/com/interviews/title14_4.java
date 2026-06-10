package com.interviews;
import java.util.HashMap;
import java.util.Map;
/**
 * @author: doom
 * @date: 2026/06/10/11:09
 * @description:
 * 力扣149. 直线上最多的点数
 */
public class title14_4 {
    public static void main(String[] args) {
        System.out.println(maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));// 3
    }
    private static int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3)
            return n;

        int ans = 1;
        for (int i = 0; i < n; i++) {
            // 对于每个基准点 i，统计斜率 -> 点数
            Map<String, Integer> slopeMap = new HashMap<>();
            int dup = 0; // 与 points[i] 重合的点数
            int currMax = 0;

            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dx = x2 - x1;
                int dy = y2 - y1;

                if (dx == 0 && dy == 0) {
                    dup++; // 重复点
                } else {
                    // 约分最简分数
                    int g = gcd(dx, dy);
                    dx /= g;
                    dy /= g;
                    // 统一符号：保证 dx > 0，或者 dx == 0 时 dy > 0
                    if (dx < 0 || (dx == 0 && dy < 0)) {
                        dx = -dx;
                        dy = -dy;
                    }
                    String key = dx + "," + dy;
                    slopeMap.put(key, slopeMap.getOrDefault(key, 0) + 1);
                }
            }

            // 计算当前基准点能得到的最大共线点数
            for (int cnt : slopeMap.values()) {
                currMax = Math.max(currMax, cnt);
            }
            // 加上重复点以及基准点本身
            currMax += dup + 1;
            ans = Math.max(ans, currMax);
        }
        return ans;
    }
    private static int gcd(int a, int b) {
        // 确保非负
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
