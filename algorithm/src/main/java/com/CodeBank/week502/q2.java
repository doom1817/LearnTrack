package com.CodeBank.week502;

/**
 * @author: doom
 * @date: 2026/05/17/15:10
 * @description:
 */
public class q2 {
    public static void main(String[] args) {
        System.out.println(countKthRoots(1, 9, 3)); //2
        System.out.println(countKthRoots(8, 30, 2)); //3
    }

    private static int countKthRoots(int l, int r, int k) {
        int count = 0;
        for (long x = 0;;x++){
            long power = powerWithLimit(x, k,r);
            if (power > r){
                break;
            }
            if (power >= l) {
                count++;
            }
        }
        return count;
    }
    private static long powerWithLimit(long base, long k, long limit) {
        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= base;
            // 一旦超过限制，立刻停止，返回一个比 limit 大的数
            if (result > limit) {
                return limit + 1;
            }
        }
        return result;
    }
}
