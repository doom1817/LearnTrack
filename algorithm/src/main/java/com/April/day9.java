package com.April;

import java.util.Arrays;

/**
 * @author: doom
 * @date: 2026/04/09/21:07
 * @description:
 * 力扣3655. 区间乘法查询后的异或 II
 */
public class day9 {
    public static void main(String[] args) {
        System.out.println(xorAfterQueries(new int[]{1,1,1}, new int[][]{{0,2,1,4}}));
        System.out.println(xorAfterQueries(new int[]{2,3,1,5,4}, new int[][]{{1,4,2,3},{0,2,1,2}}));
    }
    private static final int MOD = 1000000007;
    private static int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n); //根号分治阈值 B = 316
        int[][] diff = new int[B+1][n]; //乘法差分数组:diff[k][i] 表示在步长 k 下，对下标 i 的乘法标记
        for (int k=1;k<=B;k++){
            Arrays.fill(diff[k],1); //乘法单位元是1
        }
        for (int[] query : queries){
            int l = query[0], r = query[1], k = query[2], v = query[3];
            if (k>B){
                //1.大步长:暴力模拟
                for (int i = l; i <= r; i+=k){
                    nums[i] = (int)(((long) nums[i] * v) % MOD);
                }
            }else {
                //2.小步长:区间乘法
                //起点乘以V的标记
                diff[k][l] = (int)(((long)diff[k][l] * v) % MOD);
                int next_idx = l+((r-l)/k+1)*k;
                if (next_idx<n){
                    //在失效下标处打上“除以 v”的标记（即乘以 v 的逆元）
                    long inv =power(v,MOD-2);
                    diff[k][next_idx] = (int)(((long)diff[k][next_idx] * inv) % MOD);
                }
            }
        }
        for (int k=1;k<=B;k++){
            for (int i = k; i < n; i++){
                diff[k][i] = (int)(((long)diff[k][i] * diff[k][i-k]) % MOD);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++){
            long cur = nums[i];
            for (int k=1;k<=B;k++){
               cur = (cur * diff[k][i]) % MOD;
            }
            ans ^=(int)cur;
        }
        return ans;
    }
    private static long power(long base, long exp){
        long res = 1;
        base %= MOD;
        while (exp >0){
            if ((exp &1)==1){
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
