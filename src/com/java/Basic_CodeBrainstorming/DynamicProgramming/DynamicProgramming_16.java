package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_16
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/12 11:48
 * @Version 1.0
 */
public class DynamicProgramming_16 {
    public static void main(String[] args) {
        new Solution16().coinChange(new int[]{2}, 9);
    }
}

class Solution16 {
    /**
     * 动态规划：本题是求组成总金额所需的最小硬币数，可无限次选取硬币，故为完全背包问题
     * 确定dp数组及其下班含义：dp[j]表示凑足总额为j所需硬币的最少数目
     * 确定dp数组递推公式：因为要求最小数组，因此需要使用min进行比较，而dp[j]由两个方向得来，一个是不放入当前硬币，即保持不变为dp[j]，另一个是放入当前硬币，总硬币数+1，即dp[j-coins[i]]+1，
     * 求最小值即可：dp[j] = min(dp[j],dp[i-coins[j]]+1）
     * 确定dp数组初始化：dp[0]表示凑足总额为0所需的硬币个数，易知dp[0]=0即不放入硬币。而若dp[0]=0，dp[j-coins[i]]由前面的dp决定，会导致后面所有的dp为0，因此
     * 需要初始化为最大值，可以用 amount+1 来填充dp数组进行初始化，因为coins都是整数，最差的情况下，用硬币 1 组成 amount，那每个空格里的值也是小于 amount+1 的。
     * 确定dp数组遍历方式：由于是求硬币的最小个数，与硬币的组合或排序方式无关，故物品与背包的遍历顺序无先后之分。
     * 时间复杂度：O(m*n)，空间复杂度：O(n)
     *
     * @param coins  硬币数组
     * @param amount 总金额数
     * @return 凑足总金额数所需的最小硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        //初始化dp数组
        int[] dp = new int[amount + 1];
        //最坏情况下均使用1凑成amount，故dp最大值为amount
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        //遍历dp数组，物品与背包遍历顺序无要求
        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j])
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }

        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));
        //若dp[amount]!=amount+1，即可以硬币凑足，否则说明无法凑足
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
