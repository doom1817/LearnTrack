package com.June;

/**
 * @author: doom
 * @date: 2026/06/05/09:04
 * @description:
 *  力扣3753. 范围内总波动值Ⅱ
 */
public class day5 {
    public static void main(String[] args) {
        System.out.println(totalWaviness(120, 130));
    }

    /**
     *  数组DP
     * @param num1
     * @param num2
     * @return
     */
    private static long totalWaviness(long num1, long num2) {
        char[] lowS = Long.toString(num1).toCharArray();
        char[] highS = Long.toString(num2).toCharArray();
        int n = lowS.length;
        long[][][][] memo = new long[n][n-1][3][10];
        return dfs(0,0,0,0,true,true,lowS,highS, memo);
    }

    /**
     * 数位 DP 递归函数
     * @param i         当前处理到第 i 位（从高位开始）-> 决定递归深度
     * @param waviness  已经累计的波动值（从之前的位置中已经确定的峰/谷个数）-> 累加结果
     * @param lastCmp   上一次相邻两数比较结果：-1(下降),0(相等),1(上升)-> 延迟判断峰/谷，优化状态
     * @param lastDigit 上一位填的数字（0-9）-> 计算当前比较结果cmp,与lastCmp一起判断转折
     * @param limitLow  是否受到下界 lowS 的限制 -> 保证数字≥ num1
     * @param limitHigh 是否受到上界 highS 的限制 -> 保证数字≤ num2
     * @param lowS      下界字符数组
     * @param highS     上界字符数组
     * @param memo      记忆化数组
     * @return 从当前状态出发，所有合法数字的 **总波动值**（包含已累计的 waviness）
     */
    private static long dfs(int i,int waviness,int lastCmp,int lastDigit,boolean limitLow,boolean limitHigh,char[] lowS,char[] highS,long[][][][] memo){
        //终止条件判断
        if (i == lowS.length){
            return waviness;
        }
        // 记忆化剪枝优化-> 当不受上下界限制是使用缓存结果
        if (!limitHigh && !limitLow && memo[i][waviness][lastCmp+1][lastDigit]>0){
            return memo[i][waviness][lastCmp+1][lastDigit];
        }
        int diffLh = highS.length- lowS.length; //长度差
        int lo = limitLow&& i>=diffLh? lowS[i-diffLh]-'0' : 0; //  下届限制 且当前为对齐低界——> lo=lowS的对应位
        int hi = limitHigh ? highS[i]-'0' : 9;
        long res = 0;
        boolean isNum = !limitLow || i>diffLh; // 判断当前是否处于有效数字位 -> !limitLow 脱离下界约束;i>diff 说明当前位置超过长度差

        // 开始遍历当前位所有可能的数字d
        for (int d=lo;d<=hi;d++){
            int cmp = isNum?Integer.compare(d,lastDigit):0;
            // 若上一次比较结果与本次比较结果异号（乘积<0），则说明出现了峰或谷
            // 注意：这个判断依赖于三个连续数字：a(lastCmp), b(lastDigit), c(d)
            // 当 a<b 且 b>c 时 lastCmp>0, cmp<0 → 乘积负，b 是峰
            // 当 a>b 且 b<c 时 lastCmp<0, cmp>0 → 乘积负，b 是谷
            int w = waviness+(cmp*lastCmp<0?1:0);
            res+=dfs(i+1,w,cmp,d,limitLow&&d==lo,limitHigh&&d==hi,lowS,highS,memo);
        }
        if (!limitHigh && !limitLow){
            memo[i][waviness][lastCmp+1][lastDigit] = res;
        }
        return res;
    }
}
