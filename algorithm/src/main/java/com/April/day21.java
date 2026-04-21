package com.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doom
 * @date: 2026/04/21/09:00
 * @description:
 *  力扣1722.执行交换操作后的最小汉明距离
 */
public class day21 {
    public static void main(String[] args) {
        System.out.println(minimumHammingDistance(new int[]{1,2,3,4}, new int[]{2,1,4,5}, new int[][]{{0,1},{2,3}})); // 1
    }
    //图论+并查集
    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n); // 并查集
        //建立联通数组
        for (int[] swap : allowedSwaps){
            uf.union(swap[0], swap[1]);
        }
        //Key: 根节点, Value: <数字, 频率差>
        Map<Integer,Map<Integer, Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++){
            int root = uf.find(i);
            groups.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> count = groups.get(root);
            //统计数字的频率差
            count.put(source[i], count.getOrDefault(source[i], 0) + 1);
            count.put(target[i], count.getOrDefault(target[i], 0) - 1);
        }
        int res = 0;
        for (Map<Integer, Integer> count : groups.values()){
            for (int freq : count.values()){
               if (freq > 0){
                   res += freq;
               }
            }
        }
        return res;
    }
    //并查集
    private static class UnionFind{
        private final int[] counter ;
        private int n;
        public UnionFind(int n){
            this.n = n;
            counter = new int[n];
            //初始化—自己和自己联通
            for (int i = 0; i < n; i++) {
                counter[i] = i;
            }
        }
        //找到x的根结点
        private int find(int x){
            if (x == counter[x]){
                return x;
            }
            counter[x] = find(counter[x]);
            return counter[x];
        }
        //合并x和y
        private void union(int x, int y){
            int index1 = find(x), index2 = find(y);
            if (index1 == index2){
                return;
            }
            counter[index1] = index2;
        }
        public  boolean isConnected(int x, int y){
            return find(x) == find(y);
        }
    }
}
