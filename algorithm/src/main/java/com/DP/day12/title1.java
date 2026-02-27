package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/15/22:19
 * @Description:
 * 力扣2140. 解决智力问题
 */
public class title1 {
    public static void main(String[] args) {
        System.out.println(mostPoints(new int[][]{{3,2},{4,3},{4,4},{2,5}}));
        System.out.println(mostPoints(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}}));
    }
    /**
     * 场景分析：
     *  每个问题只有两种解法 - 选择当前问题，不选择当前问题
     *  状态定义： 只算总分 一维数组
     *  dp[i] 表示从questions[i] 开始，最多能得到的总分数 -> 简化问题，会考察所有位置是否是最佳的解
     *  所以要求 状态转移方程：dp[i]最大，dp[i] =max(questions[i][0]+dp[i+questions[i][1]+1],dp[i+1])
     *  状态分析：
     *  如果选择解决： question[i][0]+dp[i+questions[i][1]+1]
     *  如果跳过 当前问题： dp[i+1]
     */
    public static long mostPoints(int[][] questions){
        int[] dp = new int[100001];
        for(int i=questions.length-1;i>=0;i--){
             dp[i] = Math.max(
                     questions[i][0]+dp[i+questions[i][1]+1]
                     ,dp[i+1]);
        }
        return dp[0];
    }

    private  Long[] memo;
    private int[][] questions;
    private int n;
    public  long mostPoints2(int[][] questions){
            this.questions = questions;
            this.memo = new Long[n];
            this.n = questions.length;
            return dfs(0);

    }

    private long dfs(int index){
       if(index>=n) return 0L;
       if (memo[index]!=null) {return memo[index];}
       //解决当前问题
        long solveCurrent = (long) questions[index][0] +dfs(index+questions[index][1]+1);
        //跳过当前问题
        long skipCurrent = dfs(index+1);

        long res = Math.max(solveCurrent,skipCurrent);
        memo[index] = res;
        return res;
    }
}
