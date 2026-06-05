---
title: 力扣3753 - 范围内总波动值Ⅱ 数组DP解法
date: 2026-06-05
tags:
  - 算法
  - 动态规划
  - 数位DP
  - 数组DP
---

# 力扣3753 - 范围内总波动值Ⅱ 数组DP解法

## 题目描述

给定两个整数 `num1` 和 `num2`，返回 `[num1, num2]` 范围内所有整数的 **波动值** 之和。

**波动值** 定义：一个数字中峰和谷的总数。
- **峰**：三个连续数字 `a, b, c` 满足 `a < b > c`
- **谷**：三个连续数字 `a, b, c` 满足 `a > b < c`

## 数组DP核心概念

数组DP（或多维数组DP）是用数组来存储状态的动态规划方法。

### 核心思想

1. **状态**：用一组参数描述一个子问题
2. **状态转移**：通过枚举当前位的选择，把当前状态分解为更小的子状态
3. **数组**：用数组的各个维度来对应不同的状态参数，用数组元素的值存储该状态的计算结果

### 为什么使用数组DP？

- **记忆化**：避免重复计算相同子问题
- **空间换时间**：用数组缓存中间结果
- **状态压缩**：将复杂问题分解为可管理的子问题

## 状态设计分析

### 状态参数定义

对于本题，我们需要定义以下状态参数：

```java
dfs(int i, int waviness, int lastCmp, int lastDigit, 
    boolean limitLow, boolean limitHigh)
```

| 参数 | 含义 | 取值范围 |
|------|------|----------|
| `i` | 当前处理到第几位（从高位开始） | `[0, n)` |
| `waviness` | 已经累计的波动值 | `[0, n-1]` |
| `lastCmp` | 上一次相邻两数比较结果 | `{-1, 0, 1}` |
| `lastDigit` | 上一位填的数字 | `[0, 9]` |
| `limitLow` | 是否受到下界限制 | `boolean` |
| `limitHigh` | 是否受到上界限制 | `boolean` |

### 状态设计原则

1. **完整性**：状态参数必须能唯一确定一个子问题
2. **最优性**：状态转移必须能覆盖所有可能的选择
3. **无后效性**：当前状态只依赖于之前的状态，不依赖于之后的状态

## 解题思路

### 第一步：问题转化

将区间 `[num1, num2]` 的问题转化为：
```
totalWaviness(num1, num2) = solve(num2) - solve(num1 - 1)
```

但本题采用数位DP直接处理区间，通过 `limitLow` 和 `limitHigh` 控制边界。

### 第二步：数位DP框架

数位DP的核心是从高位到低位逐位处理，枚举每一位可能填入的数字。

```java
for (int d = lo; d <= hi; d++) {
    // 枚举当前位可以填入的数字d
    // 计算新的波动值
    // 递归处理下一位
}
```

### 第三步：波动值计算

关键是如何判断峰和谷：

```java
int cmp = Integer.compare(d, lastDigit);  // 当前比较结果
int w = waviness + (cmp * lastCmp < 0 ? 1 : 0);  // 如果方向改变，则计数+1
```

**原理**：
- 当 `cmp * lastCmp < 0` 时，说明方向发生了改变
- 如果之前是上升（`lastCmp > 0`），现在是下降（`cmp < 0`），则出现峰
- 如果之前是下降（`lastCmp < 0`），现在是上升（`cmp > 0`），则出现谷

### 第四步：记忆化优化

使用四维数组缓存状态：

```java
long[][][][] memo = new long[n][n-1][3][10];
// 维度：[位置][波动值][比较结果+1][上一位数字]
```

**缓存条件**：只有当不受上下界限制时才能使用缓存

```java
if (!limitHigh && !limitLow && memo[i][waviness][lastCmp+1][lastDigit] > 0) {
    return memo[i][waviness][lastCmp+1][lastDigit];
}
```

## 代码实现

```java
private static long totalWaviness(long num1, long num2) {
    char[] lowS = Long.toString(num1).toCharArray();
    char[] highS = Long.toString(num2).toCharArray();
    int n = lowS.length;
    long[][][][] memo = new long[n][n-1][3][10];
    return dfs(0, 0, 0, 0, true, true, lowS, highS, memo);
}

private static long dfs(int i, int waviness, int lastCmp, int lastDigit,
                       boolean limitLow, boolean limitHigh,
                       char[] lowS, char[] highS, long[][][][] memo) {
    // 终止条件
    if (i == lowS.length) {
        return waviness;
    }
    
    // 记忆化剪枝
    if (!limitHigh && !limitLow && memo[i][waviness][lastCmp+1][lastDigit] > 0) {
        return memo[i][waviness][lastCmp+1][lastDigit];
    }
    
    int diffLh = highS.length - lowS.length;
    int lo = limitLow && i >= diffLh ? lowS[i-diffLh] - '0' : 0;
    int hi = limitHigh ? highS[i] - '0' : 9;
    long res = 0;
    boolean isNum = !limitLow || i > diffLh;
    
    for (int d = lo; d <= hi; d++) {
        int cmp = isNum ? Integer.compare(d, lastDigit) : 0;
        int w = waviness + (cmp * lastCmp < 0 ? 1 : 0);
        res += dfs(i+1, w, cmp, d, 
                  limitLow && d == lo, limitHigh && d == hi, 
                  lowS, highS, memo);
    }
    
    if (!limitHigh && !limitLow) {
        memo[i][waviness][lastCmp+1][lastDigit] = res;
    }
    return res;
}
```

## 复杂度分析

- **时间复杂度**：$O(n \times W \times 3 \times 10 \times 10)$
  - `n`：数字位数
  - `W`：最大波动值（约等于 `n`）
  - 3：比较结果的三种状态
  - 10：上一位数字的十种可能
  - 10：当前位数字的十种可能

- **空间复杂度**：$O(n \times W \times 3 \times 10)$
  - 记忆化数组的空间

## 数组DP设计总结

### 设计步骤

1. **识别子问题**：确定需要解决的问题是什么
2. **定义状态**：用一组参数唯一标识一个子问题
3. **确定状态转移**：如何从当前状态转移到更小的子状态
4. **设计数组结构**：根据状态参数设计数组维度
5. **实现记忆化**：使用数组缓存中间结果

### 常见状态参数类型

| 参数类型 | 示例 | 说明 |
|----------|------|------|
| 位置 | `i`, `j` | 当前处理到的位置 |
| 累计值 | `sum`, `count` | 已经累计的结果 |
| 状态标志 | `flag`, `cmp` | 描述当前状态的标志 |
| 边界限制 | `limit`, `tight` | 是否受到边界限制 |

### 注意事项

1. **状态压缩**：尽量减少状态参数的数量
2. **边界处理**：注意处理边界条件和特殊情况
3. **记忆化条件**：确定何时可以使用缓存结果
4. **初始化**：正确初始化边界状态

## 扩展思考

### 类似题目

- [[数位DP基础]]
- [[状态压缩DP]]
- [[区间DP]]

### 相关概念

- [[动态规划基础]]
- [[记忆化搜索]]
- [[递归与迭代]]

---

> [!tip] 学习建议
> 理解数组DP的关键在于掌握状态设计的思想，通过练习更多题目来加深理解。
