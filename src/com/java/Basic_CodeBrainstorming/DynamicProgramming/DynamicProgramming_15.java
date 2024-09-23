package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_15
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/12 10:49
 * @Version 1.0
 */
public class DynamicProgramming_15 {
    public static void main(String[] args) {
        new Solution15().ClimbStar(3, 3);
    }
}

class Solution15 {
    /**
     * 动态规划：此题同之前做过的爬楼梯问题，区别在于之前的题目是每次只能爬一层或两层，而本题每次可以选择爬1~m层，可重复选取，并且1，2和2，1是两种不同的方式，故本题属于完全背包中的排序问题。
     * 确定dp数组的下班及其含义：dp[i]表示爬到有i个台阶的顶层，有dp[i]种方法。
     * 确定dp数组递推公式：之前爬楼梯问题每次只能爬1层或2层，当前层可由之前一层或之前两层推导而来，故dp[i] = dp[i-1] + dp[i-2]，而本题可以爬多层，故dp[i] = dp[i-1] + dp[i-2] + dp[i-3] + .... dp[i-j]
     * 确定dp数组初始化：由于dp[i] = dp[i] + dp[i-j]，后面是由前面推导而来，故dp[0] = 1。
     * 确定dp数组遍历顺序：由于1，2和2，1是两种不同的方法，故同组合总和问题，需先遍历背包容量，后遍历物品。
     * 时间复杂度：O(n*m)，空间复杂度：O(n)
     *
     * @param n 台阶的高度
     * @param m 每一步的最大步长
     */
    public void ClimbStar(int n, int m) {
        //dp数组初始化
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //遍历dp数组，先遍历背包容量，后遍历物品
        for (int i = 1; i < n + 1; i++) {
            //每步步长从1到m
            for (int j = 1; j <= m; j++) {
                if (i >= j)
                    dp[i] = dp[i] + dp[i - j];
            }
        }
        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));
    }
}
