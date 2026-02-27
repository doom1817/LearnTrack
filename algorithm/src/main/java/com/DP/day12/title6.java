package com.DP.day12;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/16/12:56
 * @Description:
 * 力扣790. 多米诺和托米诺平铺
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(numTilings(3));
    }
    private static final int MOD = 1000000007;
    public static int numTilings(int n) {
        if (n == 1) {return 1;}
        if (n == 2){return 2;}
        long full_prev2 = 1;
        long full_prev1 = 1;
        long part_prev1 = 0;

        for (int i = 2; i <= n; i++){
            long full_curr = (full_prev1+full_prev2+2*part_prev1)%MOD;
            long part_curr = (full_prev1+part_prev1+full_prev2)%MOD;

            full_prev2 = full_prev1;
            full_prev1 = full_curr;
            part_prev1 = part_curr;
        }
        return (int)full_prev1;
    }
}
