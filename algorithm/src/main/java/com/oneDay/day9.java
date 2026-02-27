package com.oneDay;

/**
 * @author: doom
 * @date: 2026/02/27/17:52
 * @description:
 * 力扣3666.使二进制字符串全为1的最短操作数
 */
public class day9 {

    public static void main(String[] args) {
        System.out.println(minOperations("110", 1)); //1
        System.out.println(minOperations("0101", 3));//2
        System.out.println(minOperations("101", 2)); //-1
        System.out.println(minOperations("001", 3)); //-1
    }

    /**
     * 模拟
     * 解题思路：
     * 1.初始'0'要被翻转奇数次，'1'要被翻转偶数次
     * 2.假设我们总共进行了 m 次操作。每次操作我们必须翻转 k 个字符，这意味着有 n-k 个字符没有被翻转。
     * 所以：翻转总额度 (Flips)： m * k 次。不翻转/休息总额度 (Non-flips)： m * (n - k) 次。
     * 3.
     * @param s
     * @param k
     * @return
     */
    public static int minOperations(String s, int k) {
        int n = s.length();
        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zero++;
            }
        }
        // 特殊情况1：没有0，无需操作
        if (zero == 0) {
            return 0;
        }
        // 特殊情况2：每次翻转整个字符串（n=k）
        if (k == n) {
            // 全0则操作1次，否则永远有0，返回-1
            return zero == n ? 1 : -1;
        }

        int ans = Integer.MAX_VALUE;
        // 情况一：操作次数 m 是偶数
        if (zero % 2 == 0) { // 数学约束：m偶 → mk偶 → z必须偶
            // 计算下界：max(ceil(z/k), ceil(z/(n-k)))
            // 上取整转换：ceil(a/b) = (a + b - 1) / b
            int m = Math.max((zero + k - 1) / k, (zero + n - k - 1) / (n - k));
            // 调整为偶数：若m是奇数，+1；偶数则不变（m%2=0 → m+0）
            ans = m + m % 2;
        }

        // 情况二：操作次数 m 是奇数
        if (zero % 2 == k % 2) { // 数学约束：m奇 → mk奇偶性=k → z需和k同奇偶
            // 计算下界：max(ceil(z/k), ceil((n-z)/(n-k)))
            int m = Math.max((zero + k - 1) / k, (n - zero + n - k - 1) / (n - k));
            // 调整为奇数：m | 1 等价于「奇数不变，偶数+1」（位运算精简写法）
            // 例：m=2 → 2|1=3；m=3 → 3|1=3
            ans = Math.min(ans, m | 1);
        }

        // 有满足条件的m则返回最小值，否则返回-1
        return ans < Integer.MAX_VALUE ? ans : -1;
    }
}
