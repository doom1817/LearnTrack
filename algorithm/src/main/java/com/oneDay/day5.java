package com.oneDay;

import java.util.HashSet;

/**
 * @author: doom
 * @date: 2026/02/23/18:44
 * @description:
 * 力扣1461. 检查一个字符串是否包含所有长度为 k 的二进制子串
 */
public class day5 {
    public static void main(String[] args) {
        System.out.println(hasAllCodes("00110110", 2));
        System.out.println(hasAllCodes("0110", 1));
        System.out.println(hasAllCodes("0110", 2));
    }
    /**
     * 思路：
     * 1. 创建一个长度为2^k的数组，用于存储所有长度为k的二进制子串
     * 2. 遍历字符串，将每个长度为k的二进制子串存储在数组中
     * 3. 判断数组中是否有空位，如果有则返回false
     * 4. 返回true
     * @param s
     * @param k
     * @return
     */
    public static boolean hasAllCodes(String s, int k) {
        int[] onesPositions = new int[1 << k]; // 创建一个长度为2^k的数组
        for (int i = 0; i < s.length() - k + 1; i++) { // 遍历字符串 , 循环的结束条件确保了子串不会越界
//            onesPositions[Integer.parseInt(s.substring(i, i + k), 2)] = 1; // 将每个长度为k的二进制子串存储在数组中
            // 提取从位置 i 开始，长度为 k 的子串
            String subStr = s.substring(i, i + k); // 例如，s="00110", k=2, 第一次循环 i=0, subStr="00"

            // 将二进制子串转换为十进制整数，作为数组的索引
            int index = Integer.parseInt(subStr, 2); // parseInt(str, 2) 将二进制字符串解析为十进制整数
            // 例如，"00" -> 0, "01" -> 1, "10" -> 2, "11" -> 3

            // 标记该子串（对应的数字）已经出现过
            onesPositions[index] = 1; // 将对应索引位置的值设为 1
        }
        for (int onesPosition : onesPositions) {
            if (onesPosition == 0) {
                return false; // 存在空位则返回false
            }
        }
        return true;
    }
    /**
     * 检查字符串 s 是否包含所有长度为 k 的二进制子串。
     *
     * 思路：
     * 1. 使用一个 boolean 数组来标记每种可能的 k 位二进制子串是否出现过。
     * 2. 使用滑动窗口技术遍历字符串 s，并通过位运算高效更新当前窗口的数值。
     * 3. 维护一个计数器 count，用于记录新发现的不同子串的数量。
     * 4. 对于每个长度为 k 的窗口，计算出其对应的数值。如果该数值对应的布尔数组位置为 false，
     *    说明这是一个新发现的子串。将其标记为 true，并将计数器加一。
     * 5. 在每次更新计数器后，检查其是否等于 2^k。如果相等，则提前返回 true。
     * 6. 遍历结束后，如果计数器仍未达到 2^k，则返回 false。
     * 7. 在循环开始前进行预检查，如果字符串长度小于 k，则直接返回 false。
     *
     * @param s 输入的二进制字符串。
     * @param k 子串的长度。
     * @return 如果 s 包含所有长度为 k 的二进制子串，则返回 true；否则返回 false。
     */
    public static boolean hasAllCodes1(String s, int k) {
        // 预检查：如果字符串长度小于 k，则无法形成任何长度为 k 的子串
        if (s.length() < k) return false;

        // 计算总共需要的不同子串数量 (2^k)
        int totalRequired = 1 << k;

        // 用于标记每个数值是否已出现的布尔数组
        boolean[] seen = new boolean[totalRequired];

        // 用于表示当前窗口内的k位数值
        int currentWindowValue = 0;

        // 用于确保窗口大小不超过k位的掩码 (mask = 11...1 k次)
        int mask = totalRequired - 1; // (1 << k) - 1

        // 新增一个计数器，替代 HashSet.size()
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // 滑动窗口：左移一位，加上新位，并用掩码限制位数
            currentWindowValue = ((currentWindowValue << 1) & mask) | (s.charAt(i) - '0');

            // 合并条件：只有当窗口大小达到k，并且当前子串是首次出现时，才进行后续操作
            if (i >= k - 1 && !seen[currentWindowValue]) {
                // 标记该子串已出现
                seen[currentWindowValue] = true;
                // 增加计数器
                count++;

                // 如果计数器达到目标数量，说明所有子串都已找到
                if (count == totalRequired) {
                    return true;
                }
            }
        }

        // 遍历结束后，计数器仍未达到目标数量
        return false;
    }

}
