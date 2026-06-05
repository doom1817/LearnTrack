package com.June;

/**
 * @author: doom
 * @date: 2026/06/04/09:04
 * @description: 力扣3751. 范围内总波动值Ⅰ
 */
public class day3 {
    public static void main(String[] args) {
        System.out.println(totalWaviness(120, 130)); //3
    }

    /**
     * 波动值就是一个数字的峰和谷的总数
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int totalWaviness(int num1, int num2) {
        //保持num1小于num2，方便后面计算
        if (num1 > num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int total = 0;
        for (int i = num1; i <= num2; i++) {
            total += waviness(i);
        }
        return total;
    }

    private static int waviness(int num) {
        if (num < 100) return 0;
        String s = Integer.toString(num);
        int len = s.length();
        int count = 0;
        //排斥首尾两个数字
        for (int i = 1; i < len - 1; i++){
            int prev = s.charAt(i - 1) - '0';
            int cur = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';
            //峰判断
            if (prev < cur && cur > next){
                count++;
            }
            //谷判断
            if (prev > cur && cur < next){
                count++;
            }
        }
        return count;
    }
}
