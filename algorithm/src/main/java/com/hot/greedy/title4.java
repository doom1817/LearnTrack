package com.hot.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: doom
 * @date: 2026/04/24/11:46
 * @description:
 * 力扣763. 划分字母区间
 */
public class title4 {
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij")); // [9,7,8]
    }
    private static List<Integer> partitionLabels(String s){
        int[] last = new int[26];
        int length =s.length();
        for(int i=0;i<length;i++){
            last[s.charAt(i)-'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0 , end = 0;
        for(int i=0;i<length;i++){
            end = Math.max(end,last[s.charAt(i)-'a']);
            if(i == end){
                res.add(end-start+1);
                start = end+1;
            }
        }
        return  res;
    }
}
