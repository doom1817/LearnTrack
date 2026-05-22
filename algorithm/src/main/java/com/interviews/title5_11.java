package com.interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/05/21/10:41
 * @description:
 *  力扣68. 文本左右对齐
 */
public class title5_11 {
    public static void main(String[] args) {
        System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
        System.out.println(fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20));
    }
    private static List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List< String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int left=0,right=0;
        while (right<n){
            int len = 0; //  当前行长度
            while (right<n && len+words[right].length()+right-left-1<maxWidth){
                len += words[right].length();
                right++;
            }
            //单词间隔数量
            int gapCount = right-left-1;
            if (right ==n || gapCount ==0){
                //最后一行
                for (int i = left; i < right-1; i++){
                    sb.append(words[i]).append(" ");
                }
                //最后一个单词
                sb.append(words[right-1]);
                //填充空格
                while (sb.length()<maxWidth){
                    sb.append(" ");
                }
            }else {
                //非最后一行
                int total = maxWidth-len; // 待插入空格总数
                int avg = total/gapCount;  //平均每个间隔空格数量
                int remainder = total%gapCount; // 剩余空格数量
                for (int i = left; i < right-1; i++){
                    sb.append(words[i]);
                    sb.append(" ".repeat(Math.max(0, avg)));
                    if(i-left<remainder){
                        sb.append(" ");
                    }
                }
                sb.append(words[right-1]);
            }
            ans.add(sb.toString());
            sb.setLength(0);
            left = right;
        }
        return ans;
    }
}
