package com.hot.BinarySearch;

/**
 * @author: doom
 * @date: 2026/04/18/15:20
 * @description:
 *  力扣4.寻找两个正序数组的中位数
 */
public class title6 {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
//        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4,5})); //3
//        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4,5,6})); //3.5
    }
    private static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        // 如果数组长度为奇数，则返回第 k 小的元素
        if ((len & 1) == 1){
            return findKth(A, B, len/2+1);
        }else {
            return (findKth(A, B, len/2) + findKth(A, B, len/2+1))/2.0;
        }
    }

    /**
     * 在两个有序数组中寻找第 k 小的元素
     */
    private static int findKth(int[] A, int[] B, int k) {
        int m = A.length, n = B.length;
        int idxA = 0, idxB = 0;

        while (true) {
            // 如果一个数组已经空了，直接从另一个数组取第 k 小
            if (idxA == m) {
                return B[idxB + k - 1];
            }
            if (idxB == n) {
                return A[idxA + k - 1];
            }
            // 如果只需找第 1 小，直接比较当前元素
            if (k == 1) {
                return Math.min(A[idxA], B[idxB]);
            }

            // 正常情况：尝试比较两个数组中第 k/2 个元素
            int half = k / 2;
            int newIdxA = Math.min(idxA + half - 1, m - 1);
            int newIdxB = Math.min(idxB + half - 1, n - 1);

            int pivotA = A[newIdxA];
            int pivotB = B[newIdxB];

            if (pivotA <= pivotB) {
                // 排除 A 中 [idxA, newIdxA] 这部分元素
                k -= (newIdxA - idxA + 1);
                idxA = newIdxA + 1;
            } else {
                // 排除 B 中 [idxB, newIdxB] 这部分元素
                k -= (newIdxB - idxB + 1);
                idxB = newIdxB + 1;
            }
        }
    }
}
