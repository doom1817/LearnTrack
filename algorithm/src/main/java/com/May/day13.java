package com.May;

/**
 * @author: doom
 * @date: 2026/05/13/09:13
 * @description:
 *  力扣1674.使数组互补的最少操作次数
 */
public class day13 {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1,2,4,3}, 4)); // 1
        System.out.println(minMoves(new int[]{1,2,2,1}, 2)); // 2
        System.out.println(minMoves(new int[]{1,2,1,2}, 2)); // 0
    }

    /**
     *  首先肯定要确认这个同一个数也就是num[i]+nums[n-1-i]的和
     *  然后就可以进行最少操作数的比较
     * @param nums
     * @param limit
     * @return
     */
    private static int minMoves(int[] nums,int limit){
        int n = nums.length;
        int maxSum = 2 * limit;
        int[] diff = new int[maxSum + 3];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            int low = Math.min(a, b);
            int high = Math.max(a, b);
            int sum = a + b;

            // 区间 [low+1, high+limit] 内操作数减 1
            diff[low + 1] -= 1;
            diff[high + limit + 1] += 1;
            // 点 sum 处操作数再减 1
            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }

        int cur = n;          // 初始每对需要 2 次操作，总操作数 = n
        int ans = n;
        for (int T = 2; T <= maxSum; T++) {
            cur += diff[T];
            ans = Math.min(ans, cur);
        }
        return ans;
    }
}
