package com.May;

/**
 * @author: doom
 * @date: 2026/05/25/10:41
 * @description:
 *  力扣1871. 跳跃游戏 Ⅶ
 */
public class day25 {
    public static void main(String[] args) {
        System.out.println(canReach("011010",2,3));//true
        System.out.println(canReach("01101110",2,3));//false
    }

    /**
     * 判断能否从字符串的第一个位置跳跃到最后一个位置
     *
     * 跳跃规则：
     * 1. 从下标 i 跳跃到下标 j，需满足：i + minJump <= j <= min(i + maxJump, s.length - 1)
     * 2. 只能跳跃到值为 '0' 的位置
     * 3. 起始位置 s[0] 必须为 '0'
     *
     * 算法思路：
     * 使用动态规划结合滑动窗口优化。维护一个窗口 [i - maxJump, i - minJump]，
     * 统计窗口内可以到达的位置数量（dp 值为 true 的个数）。
     * 如果当前位置为 '0' 且窗口内有可到达的位置，则当前位置也可到达。
     *
     * @param s 二进制字符串，'0' 表示可站立的位置，'1' 表示不可站立的位置
     * @param minJump 最小跳跃距离
     * @param maxJump 最大跳跃距离
     * @return 如果能从起点跳跃到终点返回 true，否则返回 false
     */
    private static boolean canReach(String s,int minJump,int maxJump){
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int active  = 0 ;

        for (int i=1;i<n;i++){
            // 将新进入窗口的元素加入统计
            if (i>=minJump && dp[i-minJump]){
                    active++;
            }
            // 将刚离开窗口的元素从统计中移除
            if (i>maxJump && dp[i-1-maxJump]){
                active--;
            }
            // 如果当前位置为 '0' 且窗口内存在可到达的位置，则当前位置可达
            if (s.charAt(i)=='0'&& active>0){
                dp[i] = true;
            }
        }

        return dp[s.length()-1];
    }

    /**
     * 使用差分数组优化的解法判断能否从起点跳跃到终点
     *
     * 跳跃规则：
     * 1. 从下标 i 跳跃到下标 j，需满足：i + minJump <= j <= min(i + maxJump, s.length - 1)
     * 2. 只能跳跃到值为 '0' 的位置
     * 3. 起始位置 s[0] 必须为 '0'
     *
     * 算法思路：
     * 使用差分数组标记每个位置可以被跳到的次数。核心思想是：
     * - 如果位置 i 可以被跳到（sumD > 0）且 s[i] == '0'，则从位置 i 出发可以跳到区间 [i+minJump, i+maxJump]
     * - 通过差分数组的区间更新操作，将该区间内所有位置的标记次数加 1
     * - 遍历时维护前缀和 sumD，表示当前位置被标记的次数
     * - 最终检查终点是否被标记过且为 '0'
     *
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param s 二进制字符串，'0' 表示可站立的位置，'1' 表示不可站立的位置
     * @param minJump 最小跳跃距离
     * @param maxJump 最大跳跃距离
     * @return 如果能从起点跳跃到终点返回 true，否则返回 false
     */
    public static boolean canReachDiff(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] diff = new int[n + 1];

        // 初始化：起点 0 可以被跳到，对差分数组 [0, 0] 区间加 1
        diff[0] = 1;
        diff[1] = -1;

        int sumD = 0;
        for (int i = 0; i < n; i++) {
            // 累加差分数组得到当前位置被标记的次数
            sumD += diff[i];

            // 如果当前位置可达且为 '0'，则从该位置可以跳到后续区间
            if (sumD > 0 && s.charAt(i) == '0') {
                // 对可达区间 [i+minJump, i+maxJump] 进行标记
                diff[Math.min(i + minJump, n)]++;
                diff[Math.min(i + maxJump + 1, n)]--;
            }
        }

        // 检查终点是否可达且为 '0'
        return sumD > 0 && s.charAt(n - 1) == '0';
    }

}
