package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_6
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/31 18:16
 * @Version 1.0
 */
public class DynamicProgramming_6 {
    public static void main(String[] args) {
        new Solution6().integerBreak(10);
    }
}

class Solution6 {
    /**
     * 动态规划
     * 确定dp数组及其下标的含义：dp[i]表示数字i的最大乘积为dp[i]
     * 确定dp数组的递推公式：dp[i]表示数字i的最大乘积，因此有两个方向可以得到，一是dp[i] = j * (i - j)，如 10 = 1 * 9 或 2 * 8 等，另一个是dp[i] = j * dp[i-j]，
     * 之所以不写成dp[i] = dp[j] * dp[i-j]是是因为j是从1遍历到i的，在遍历过程中dp[i-j]是包括了j的，所以写成j * dp[i-j]即可
     * 确定dp数组的初始化：dp[0] = dp[1] = 0
     * 确定dp数组遍历顺序：i从2开始一直到n，而j从1开始一直到i，又因此dp[i]是由dp[i - j]确定的，因此一定是从前往后遍历
     * 时间复杂度：O(2^n)，空间复杂度：O(n)
     *
     * @param n 待确定的数字
     * @return 该数字的最大乘积结果
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        //dp数组初始化
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(j * dp[i - j], j * (i - j)), dp[i]);
            }
        }
        //遍历dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));

        return dp[n];
    }
}
