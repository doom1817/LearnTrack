package com.June;

/**
 * @author: doom
 * @date: 2026/06/14/14:57
 * @description:
 *  力扣3838. 带权单词映射
 */
public class day13 {
    public static void main(String[] args) {
        System.out.println(mapWordWeights(new String[]{"abcd","def","xyz"},new int[]{5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2}));
        //"rij"
    }
    private static String mapWordWeights(String[] words, int[] weights) {
        int n = words.length;
        char[] res = new char[n];
        for(int i = 0; i < n; i++){
            int sum = 0;
            for (char c : words[i].toCharArray()){
                sum+=weights[c-'a'];
            }
            res[i] = (char)('a' + sum % 26);
        }
        return new String(res);
    }
}
