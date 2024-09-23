package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_2
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/28 14:58
 * @Version 1.0
 */
public class DynamicProgramming_2 {
    public static void main(String[] args) {
        new Solution2().climbStairs(5);
    }
}

class Solution2 {
    /**
     * 动态规划：
     * 确定dp数组及其下标含义：dp[i]表示爬到第i层楼梯，有dp[i]种方法
     * 确定递推公式：dp[i]可以从两个方向得出，首先是dp[i-1]，爬到i-1层有dp[i-1]种方法，再爬一层就可以到第i层，其次是dp[i-2]，爬到i-2层有dp[i-2]种方法，再爬两层就到第i层
     * 因此dp[i] = dp[i-1] + dp[i-2]，至于为什么从i-2层爬到i层选择爬2个台阶，而不是爬1个台阶走两次，因为后者爬1步到i-1时相当于从i-1再重新爬到i，进行了覆盖，所以只需考虑一种情况即可
     * 确定dp数组初始值：爬到第一层台阶只需1步，第二层台阶只需2步，因此dp[1] = 1,dp[2] = 2
     * 确定遍历顺序：从前往后遍历
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param: n
     * @return: int 爬到第n层楼梯的方法总数
     **/
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        //打印dp数组情况进行验证
        for (int i = 1; i < dp.length; i++) {
            System.out.println(dp[i]);
        }
        return dp[n];
    }
}
