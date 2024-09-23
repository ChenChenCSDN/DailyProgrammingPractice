package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * ClassName: DynamicProgramming_3
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/7/28 15:23
 * @Version 1.0
 */
public class DynamicProgramming_3 {
    public static void main(String[] args) {
        new Soultion3().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
    }
}

class Soultion3 {
    /**
     * 动态规划：
     * 确定dp数组及其下标含义：dp[i]表示到达第i个台阶所花费的最少体力
     * 确定dp数组递推公式：dp[i]可以有两个途径得到，分别是dp[i-1]和dp[i-2]，i-1跳到i需要花费dp[i-1] + cost[i-1]，i-2跳到i需要花费dp[i-2] + cost[i-2]，选择花费最小的一种方式跳，因此dp[i] = min(dp[i-1] + cost[i-1]，dp[i-2] + cost[i-2])
     * 确定dp数组初始化：因为题意说到可以选择从下标为0或1开始起跳，因此达到第0和1个台阶不需要任何花费，故dp[0] = dp[1] = 0
     * 确定遍历方式：从前往后依次遍历cost数组
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param: cost
     * @return: int
     **/
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 2)
            return Math.min(cost[0], cost[1]);
        int len = cost.length;
        int[] dp = new int[len + 1];
        //dp数组初始化
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= len; i++) {
            //dp数组递推式
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        //打印dp数组情况，验证是否正确
        for (int i : dp) {
            System.out.println(i);
        }
        return dp[len];
    }
}
