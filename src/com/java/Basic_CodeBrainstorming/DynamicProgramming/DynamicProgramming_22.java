package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_22
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/17 10:24
 * @Version 1.0
 */
public class DynamicProgramming_22 {
    public static void main(String[] args) {
        new Solution22().maxProfit2(new int[]{7, 1, 5, 3, 6, 4});
    }
}

class Solution22 {
    /**
     * 动态规划：当前题意表明只有一次的股票买入和卖出的机会，求所得利润的最大值。某一天的利润情况分为两种，一种是持有当前股票所能获得的利润，另一种是当天购入股票所能获得的利润。
     * 确定dp数组及其下标含义：dp[i][0]表示第i天持有（不等于买入）股票所得的最大利润，dp[i][1]表示第i天不持有（不等于卖出）股票所得的最大利润。
     * 确定dp数组的递推公式：dp[i][0]可由两种情况得到：第i-1天就持有股票，所得利润就是i-1天持有股票的利润，即dp[i-1][0]，另一种情况是第i天购入股票，所得利润为-prices[i]，dp[i][1]也可由两种情况得到：第i-1天就不持有股票，所得利润就是i-1天不持有股票：dp[i-1][1]，
     * 另一种情况是第i天卖出股票，不持有股票的利润为第i-1天持有股票的利润+当天股票的价格prices[i]：dp[i][1] = dp[i-1][0] + prices[i]，又因为是要求最大利润，故取最大值：dp[i][0] = max{dp[i-1][0]，-prices[i]}，dp[i][1] = {dp[i-1][1]，dp[i-1][0] + prices[i]}
     * 确定dp数组初始化：由递推公式可知，需要对dp[0][0]和dp[0][1]进行初始化，而dp[0][0]即第0天持有股票的利润，之前无法持有股票，故只能购入股票，则dp[0][0] = -prices[0]，dp[0][1]即第0天不持有股票的利润，而之前没有购入过股票，故为0，即dp[0][1] = 0
     * 确定dp数组遍历顺序：由递推公式可知需从前往后遍历dp数组
     * 时间复杂度；O(n)，空间复杂度：O(n*2)
     *
     * @param prices 股票价格数组
     * @return 所能获得的最大利润
     */
    public int maxProfit(int[] prices) {
        //初始化dp数组
        int[][] dp = new int[prices.length][2];
        //dp[0][0]即第0天持有股票的利润，之前无法持有股票，故只能购入股票
        dp[0][0] = -prices[0];
        //dp[0][1]即第0天不持有股票的利润，而之前没有购入过股票，故为0
        dp[0][1] = 0;

        //遍历dp数组，从前往后遍历
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[prices.length - 1][1]即最后一天不持有股票所获的最大利润
        return dp[prices.length - 1][1];
    }

    /**
     * 暴力解法：由于只能一次买入和一次卖出，因此可以计算出所有情况下买入卖出的最大利润，取出最大值即可
     * 时间复杂度；O(n^2)，空间复杂度：O(1)
     * 运行超时
     *
     * @param prices 股票价格数组
     * @return 所能获得的最大利润
     */
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                //记录当前利润最大值
                maxProfit = Math.max(maxProfit, (prices[j] - prices[i]));
            }
        }
        return maxProfit;
    }
}
