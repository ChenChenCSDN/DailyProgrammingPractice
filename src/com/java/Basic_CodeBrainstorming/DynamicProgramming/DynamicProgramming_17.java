package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_17
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/12 17:04
 * @Version 1.0
 */
public class DynamicProgramming_17 {
    public static void main(String[] args) {
        new Solution17().numSquares(3);
    }
}

class Solution17 {
    /**
     * 动态规划：将题意进行转换：完全平方数就是物品（可以无限选取，凑成的正整数n就是背包容量，问凑足背包n的最小物品个数），由此可以看出此题同零钱兑换，也是完全背包问题，
     * 每个物品都是完全平方数，从1，4，9，16一直到根号（n），由这些物品选择装入背包中
     * 确定dp数组及其下标含义：dp[j]表示和为j的完全平方数的最小数量
     * 确定dp数组递推公式：由于是求最小数量，故需要进行必读选择最小的情况，dp[j]可由两个方向得来，一个是不放入当前完全平方数，另一个是放入当前完全平方数，则dp[j] = min(dp[j]，dp[j-i*i]+1）
     * 确定dp数组初始化：由题意易知dp[0]=0，而dp[j]最大不超过n（全为1组成），故将所有dp数组元素初始化为n+1
     * 确定dp数组遍历顺序：由于是完全背包问题，故对背包容量从前往后遍历；其次由于求最小数量，故与物品和背包的先后遍历顺序无关
     * 时间复杂度：O(n*m)，空间复杂度：O(n)
     *
     * @param n 目标整数
     * @return 和为目标整数的最小完全平方数的个数
     */
    public int numSquares(int n) {
        //dp数组初始化
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;

        //先遍历物品，后遍历背包
        for (int i = 1; i * i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                //确定当前物品（完全平方数）能够放入背包
                if (j >= i * i) dp[j] = Math.min(dp[j], dp[j - i * i] + 1);//dp[j-i*i]表示未放入当前完全平方数前组成该数字的完全平方数的个数
            }
        }

        //先遍历背包，后遍历物品也可以
//        for (int i = 1; i < n + 1; i++) {
//            for (int j = 1; j * j < n + 1; j++) {
//                if (i >= j * j)
//                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
//            }
//        }
        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));
        //dp[n]表示和为n的最小完全平方数的个数
        return dp[n];
    }
}
