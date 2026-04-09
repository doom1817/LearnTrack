package com.April;

/**
 * @author: doom
 * @date: 2026/04/09/20:46
 * @description:
 *  力扣3653. 区间乘法查询后的异或 I
 */
public class day8 {
    public static void main(String[] args) {
        System.out.println(xorAfterQueries(new int[]{1,1,1}, new int[][]{{0,2,1,4}}));
        System.out.println(xorAfterQueries(new int[]{2,3,1,5,4}, new int[][]{{1,4,2,3},{0,2,1,2}}));
    }
    private static  final int MOD = 1000000007;
    private static int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] query : queries) {
            int l = query[0], r = query[1], k = query[2], v = query[3];
            for (int idx =l; idx <= r; idx+=k){
                nums[idx] =(nums[idx] * v) % MOD;
            }
        }
        int ans = 0;
        for (int i:nums){
            ans ^= i;
        }
        return ans;
    }
}
