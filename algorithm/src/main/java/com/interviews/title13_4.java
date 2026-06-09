package com.interviews;


/**
 * @author: doom
 * @date: 2026/06/09/15:06
 * @description: 力扣4. 寻找两个正序数组的中位数
 */
public class title13_4 {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));//2.5
    }

    private static double findMedianSortedArrays(int[] A, int[] B) {
        // 保证A为较短的数组，优化二分查找效率
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }
        int m = A.length;
        int n = B.length;
        int left = 0, right = m;
        // halfLen表示合并后左半部分的元素个数（包含中位数）
        int halfLen = (m + n + 1) / 2;
        while (left <= right) {
            // i表示A中分割线右侧的第一个元素索引
            int i = (left + right) / 2;
            // j表示B中分割线右侧的第一个元素索引，保证左右两部分元素个数相等
            int j = halfLen - i;
            // A的分割线太靠左，需要右移
            if (i < right && B[j - 1] > A[i]) {
                left = i + 1;
            }
            // A的分割线太靠右，需要左移
            else if (i > left && A[i - 1] > B[j]) {
                right = i - 1;
            } else {
                // 找到正确的分割位置，计算左半部分的最大值
                int maxLeft = 0;
                if (i == 0) {
                    // A左半部分为空，取B左半部分的最大值
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    // B左半部分为空，取A左半部分的最大值
                    maxLeft = A[i - 1];
                } else {
                    // 取两个数组左半部分的最大值
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                // 如果总元素个数为奇数，中位数就是左半部分的最大值
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                // 计算右半部分的最小值
                int minRight = 0;
                if (i == m) {
                    // A右半部分为空，取B右半部分的最小值
                    minRight = B[j];
                } else if (j == n) {
                    // B右半部分为空，取A右半部分的最小值
                    minRight = A[i];
                } else {
                    // 取两个数组右半部分的最小值
                    minRight = Math.min(A[i], B[j]);
                }
                // 总元素个数为偶数，中位数为左右两部分边界值的平均值
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
