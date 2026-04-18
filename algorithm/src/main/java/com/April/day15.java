package com.April;

/**
 * @author: doom
 * @date: 2026/04/15/09:03
 * @description:
 *  力扣2515.到目标字符串的最短距离
 */
public class day15 {
    public static void main(String[] args) {
        System.out.println(closestTarget(new String[]{
                "hello", "i", "am", "a", "student", "from", "a", "university", "in", "a", "city"
        },"hello",1));
    }

    //全局遍历：遍历整个数组，找到所有目标字符串的位置，计算每个位置到起点的最短环形距离。
    public static int closestTarget(String[] words, String target, int startIndex) {
        int ans = Integer.MAX_VALUE;
        int n = words.length;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(target)){
                int dist  = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(dist,n - dist));
            }
        }
        return ans <n ? ans : -1;
    }

    // 双向扩展法：从 startIndex 开始，像波纹一样向左右两个方向同时扩展，逐层检查，第一次遇到目标时的距离就是最短距离。
    private static int closestTarget2(String[] words, String target, int startIndex) {
        int n = words.length;
        for (int k =0;k<=n/2;k++){
            if (words[(startIndex-k+n)%n].equals( target)|| words[(startIndex+k)%n].equals( target)){
                return k;
            }
        }
        return -1;
    }
}
