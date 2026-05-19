package com.May;

/**
 * @author: doom
 * @date: 2026/05/19/10:27
 * @description:
 *  力扣1674. 使数组互补的最少操作次数
 */
public class day15 {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1,2,4,3}, 4));//1
    }
    /**
     * 计算使数组互补的最少操作次数
     * 对于长度为n的数组，需要使nums[i] + nums[n-1-i]都等于同一个目标值
     * 每次操作可以将数组中的任意元素替换为[1, limit]范围内的值
     *
     * @param nums 输入的正整数数组
     * @param limit 每个元素可以替换的最大值（替换范围为[1, limit]）
     * @return 使所有对称对之和相等所需的最少操作次数
     */
    static int minMoves(int[] nums, int limit) {
        // 差分数组，用于记录每个目标和对应的操作次数变化
        int[] diff = new int[2 * limit + 3];
        int n = nums.length;
        int pairs = n / 2;

        // 遍历所有对称对，构建差分数组
        for (int i = 0; i < pairs; i++){
            int a = nums[i];
            int b = nums[n - i - 1];
            if (a>b){
                int temp = a;
                a = b;
                b = temp;
            }
            int low = a + 1;
            int high = b + limit;
            int sum = a + b;

            // 使用差分数组标记不同区间的操作次数变化
            // 在[low, high]范围内，这对数至少有一个不需要操作
            diff[low] += 1;
            diff[high + 1] -= 1;
            // 在sum这个点，这对数完全不需要操作，额外再节省一次
            diff[sum] += 1;
            diff[sum + 1] -=1;
        }

        // 遍历所有可能的目标和，找到最大节省次数（即最少操作次数的对立面）
        int maxSave = 0;
        int curr = 0;
        for (int target = 2; target <= 2 * limit; target++) {
            curr += diff[target];
            maxSave = Math.max(maxSave, curr);
        }

        // 最少操作次数 = 总对数*2 - 最大节省次数
        return 2 * pairs - maxSave;
    }
}
