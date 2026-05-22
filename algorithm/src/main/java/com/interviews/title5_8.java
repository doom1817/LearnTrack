package com.interviews;

/**
 * @author: doom
 * @date: 2026/05/21/09:35
 * @description:
 * 力扣151. 反转字符串中的单词
 */
public class title5_8 {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
    }
    //双指针
    private static String reverseWords(String s){
        s = s.trim(); //去掉首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder sb = new StringBuilder();
        while (i >= 0){
            while (i>=0 && s.charAt(i) != ' ') i--; //找到单词的左边界
            sb.append(s, i + 1, j + 1).append(" "); //添加单词
            while (i>=0 && s.charAt(i) == ' ') i--; //跳过单词间空格
            j = i;  //移动右指针
        }
        return sb.toString().trim();
    }
}
