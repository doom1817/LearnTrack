package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : doom
 * @date: 2026/02/16/12:38
 * @description:
 *  力扣91. 解码方法
 */
public class title4 {

    public static void main(String[] args) {
        System.out.println(new title4().numDecodings("226"));
    }
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // 使用滚动变量替代数组
        // prevPrev 代表 dp[i-2]
        // prev 代表 dp[i-1]
        // curr 代表 dp[i]

        // 初始状态
        int prevPrev = 1; // dp[0] = 1
        int prev = 1;     // dp[1] = 1

        for (int i = 2; i <= n; i++) {
            int curr = 0; // 初始化当前值

            // 尝试单字符解码
            if (s.charAt(i - 1) != '0') {
                curr = prev; // curr = dp[i-1]
            }

            // 尝试双字符解码
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0'); // 推荐写法，避免 substring 开销
            if (twoDigits >= 10 && twoDigits <= 26) {
                curr += prevPrev; // curr += dp[i-2]
            }

            // 更新滚动变量，为下一次迭代做准备
            prevPrev = prev; // dp[i-2] 更新为旧的 dp[i-1]
            prev = curr;     // dp[i-1] 更新为当前计算出的 dp[i]
        }

        // 循环结束后，prev 代表的就是 dp[n]
        return prev;
    }
}
