package com.oneDay;

/**
 * @author: doom
 * @date: 2026/02/26/11:20
 * @description:
 * 力扣1404.将二进制表示减到1的步骤数
 */
public class day8 {
    public static void main(String[] args) {
        System.out.println(numSteps("1101"));
        System.out.println(numSteps("10"));
        System.out.println(numSteps("11001"));
    }
    /**
     * 计算将二进制字符串转换为1所需的最小操作步数。
     *
     * <p><strong>核心思想：</strong>
     * 1. 避免将超长二进制字符串转换为十进制（防止整数溢出）
     * 2. 从低位到高位（从右到左）模拟二进制操作：
     *    - 偶数：直接右移（除以2）→ 1步
     *    - 奇数：先加1（使变为偶数）再右移 → 2步
     * 3. 用进位（carry）模拟二进制加法进位，避免逐位修改字符串
     *
     * @param s 二进制字符串（不含前导零，且表示正整数）
     * @return 将s转换为1所需的最小操作步数
     */
    public static int numSteps(String s) {
        // 将二进制字符串转换为字符数组，方便逐位处理
        char[] c = s.toCharArray();
        int steps = 0;    // 记录总操作步数
        int carry = 0;    // 记录当前位的进位（模拟加法进位）

        // 从最低位（末尾）向高位处理，直到第二高位（索引1）
        for (int i = c.length - 1; i > 0; i--) {
            // 计算当前位（含进位）的值
            int bit = (c[i] - '0') + carry;

            // 情况1：当前位为偶数（0或2）
            if (bit % 2 == 0) {
                steps++; // 直接右移（除以2）→ 1步
                carry = bit / 2; // 更新进位（0/2=0, 2/2=1）
            }
            // 情况2：当前位为奇数（1或3）
            else {
                steps += 2; // 加1（1步）+ 右移（1步）→ 共2步
                carry = 1;  // 加1后必然产生进位
            }
        }

        // 处理最高位（索引0）：
        // - carry=1表示最高位需要进位（如11→100，需额外1步）
        // - carry=0表示最高位已为1，无需额外操作
        return steps + carry;
    }
    /**
     * 优化后的方法，使用位运算来处理进位。
     *
     * @param s 二进制字符串（不含前导零，且表示正整数）
     * @return 将s转换为1所需最小操作步数
     */
    public static int numSteps2(String s) {
        int n = s.length();
        int ans = n - 1; // 预先计算所有位右移操作的基础步数
                        // n位二进制数需要n-1次右移才能只剩最高位
        int carry = 0;   // 进位标志，初始为0表示无进位
        for (int i = n - 1; i > 0; i--) {
            int sum = s.charAt(i) - '0' + carry;
            ans += sum % 2; // 如果当前位是1（奇数），需要额外加1操作
            carry = (sum + sum % 2) / 2; // 更新进位（同时处理加1后的进位）
                                        // 等价于：
                                        // - 偶数情况：carry = sum/2
                                        // - 奇数情况：carry = (sum+1)/2 = (sum+sum%2)/2
        }
        return ans + carry; // 最高位进位需要额外一次右移
    }
}
