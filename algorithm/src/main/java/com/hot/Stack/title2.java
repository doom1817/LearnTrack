package com.hot.Stack;

import java.util.Map;
import java.util.Stack;

/**
 * @author: doom
 * @date: 2026/04/21/09:45
 * @description:
 *  力扣394. 字符串解码
 */
public class title2 {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }
    /**
     * 解码经过编码的字符串
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次
     *
     * 算法思路：
     * 使用两个栈分别存储每层的重复次数和字符串前缀
     * 遇到数字时累加构建完整的倍数
     * 遇到 '[' 时将当前状态入栈并重置
     * 遇到 ']' 时弹出栈顶状态，构建重复字符串并与前缀拼接
     *
     * @param s 编码后的字符串，格式为 k[encoded_string]，其中 k 为正整数
     * @return 解码后的字符串
     */
    public static String decodeString(String s) {
        Stack<StringBuilder> stackString = new Stack<>(); // 存储当前层的字符串前缀
        Stack<Integer> stackInt = new Stack<>(); // 记录当前层的重复次数
        StringBuilder res = new StringBuilder(); // 记录当前层的字符串前缀
        int num = 0; // 记录数字
        //遍历字符串，根据不同字符类型执行相应操作
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //判断数字，因为数字可能为多位数，所以需要累加
            if (c<='9' && c>='0'){
                num = num * 10 + (c - '0');
            }
            //遇到 '[' 时，将当前状态入栈并重置
            else if (c == '['){
                // 将数字和string入栈
                stackInt.push(num);
                stackString.push(res);
                // 清空res
                res= new StringBuilder();
                num = 0;
            }
            // 遇到 ']' 时，弹出栈顶状态，构建重复字符串并与前缀拼接
            else if (c == ']'){
                // 获取重复次数和上一层前缀
                int count = stackInt.pop();
                StringBuilder prefix = stackString.pop();

                // 构造重复后的字符串：prefix + (当前res重复count次)
                res = prefix.append(String.valueOf(res).repeat(Math.max(0, count))); // 拼接后更新res
            }else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
