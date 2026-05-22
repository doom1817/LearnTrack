package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/21/10:06
 * @description: 力扣6. Z 字形变换
 */
public class title5_9 {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));//PINALSIGYAHRPI
        System.out.println(convert("PAYPALISHIRING", 4)); //PINALSIGYAHRPI
    }

    private static String convert(String s, int numRows) {
        //边界条件
        if (s == null || s.isEmpty()) {
            return s;
        }
        if (numRows == 1) {
            return s;
        }
        //创建一个二维数组
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        //初始化当前行和方向
        int currentRow = 0, direction = -1;
        //遍历字符串
        for (char c : s.toCharArray()) {
            sb[currentRow].append(c); //添加字符
            //更新当前行和方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                direction = -direction;
            }
            currentRow += direction;
        }
        //将二维数组拼接成字符串
        StringBuilder res = new StringBuilder();
        for (StringBuilder string : sb) {
            res.append(string);
        }
        return res.toString();
    }
}
