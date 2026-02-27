package com.hot.slidingWindow;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: doom
 * @Date: 2026/02/22/17:16
 * @Description:
 * 力扣3258.统计满足k约束的子数组的数目
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(countKConstraintSubstrings("10101",1));
        System.out.println(countKConstraintSubstrings("110101",2));
        System.out.println(countKConstraintSubstrings("00101001",2));
    }

    public static int countKConstraintSubstrings(String s, int k) {
        int n= s.length(), res = 0;
        for (int i = 0; i < n; i++){
            int[] count = new int[2];
            for (int j = i; j < n; j++){
                count[s.charAt(j) - '0']++; // 统计0和1的个数，将字符转为数字
                if (count[0]>k && count[1]>k){
                    break;
                }
                res++;
            }
        }
        return res;
    }
}
