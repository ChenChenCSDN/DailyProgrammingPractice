package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_23
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/17 13:44
 * @Version 1.0
 */
public class DynamicProgramming_23 {
    public static void main(String[] args) {
        new Solution23().maxProfit2(new int[]{7, 1, 5, 3, 6, 4});
    }
}

class Solution23 {
    /**
     * 贪心算法：由于股票可以在同一天买入，同一天卖出，没有次数限制，将全局最大利润拆分成局部最大利润，因为全局最大利润可以由许多个局部利润构成，局部利润直接不会相互影响。
     * 时间复杂度：O(n)，空间复杂度：O(1)
     *
     * @param prices 股票价格数组
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        int balance = 0;
        for (int i = 1; i < prices.length; i++) {
            //收割每一天的正利润累加，即买卖多次获得利益
            balance += Math.max(prices[i] - prices[i - 1], 0);
        }
        return balance;
    }

    /**
     * 动态规划：
     * 确定dp数组及其下标含义：dp[i][0]表示第i个持有股票的利润，dp[i][1]表示第i天不持有股票的利润。
     * 确定dp数组递推公式：可由两种情况得到：第i-1天就持有股票，所得利润就是i-1天持有股票的利润，即dp[i-1][0]，另一种情况是第i天购入股票，由于可以重复购入和卖出股票，故所得利润为第i-1天不持有股票时的利润，其中包含了购买了其他股票并卖出的情况，则第i所得利润为dp[i-1][1] - prices[i]
     * dp[i][1]也可由两种情况得到：第i-1天就不持有股票，所得利润就是i-1天不持有股票：dp[i-1][1]，另一种情况是第i天卖出股票，不持有股票的利润为第i-1天持有股票的利润+当天股票的价格prices[i]：dp[i][1] = dp[i-1][0] + prices[i]，又因为是要求最大利润，故取最大值：dp[i][0] = max{dp[i-1][0]，-prices[i]}，dp[i][1] = {dp[i-1][1]，dp[i-1][0] + prices[i]}
     * 注：本题同121.买卖股票的最佳时机问题唯一不同的地方，就是推导dp[i][0]的时候，第i天买入股票的情况。在121.买卖股票的最佳时机中，因为股票全程只能买卖一次，所以如果买入股票，那么第i天持有股票即dp[i][0]一定就是 -prices[i]。
     * 而本题，因为一只股票可以买卖多次，所以当第i天买入股票的时候，所持有的现金可能有之前买卖过的利润。那么第i天持有股票即dp[i][0]，如果是第i天买入股票，所得现金就是昨天不持有股票的所得现金 减去 今天的股票价格 即：dp[i - 1][1] - prices[i]。其他情况同121.买卖股票的最佳时机问题
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param prices 股票价格数组
     * @return 最大利润
     */
    public int maxProfit2(int[] prices) {
        //初始化dp数组
        int[][] dp = new int[prices.length][2];
        //dp[0][0]即第0天持有股票的利润，之前无法持有股票，故只能购入股票
        dp[0][0] = -prices[0];
        //dp[0][1]即第0天不持有股票的利润，而之前没有购入过股票，故为0
        dp[0][1] = 0;

        //遍历dp数组，从前往后遍历
        for (int i = 1; i < prices.length; i++) {
            //dp[i - 1][1] - prices[i]表示第i-1天不持有股票时所获的利润再减去第i天购买股票的钱，就是第i天不持有股票的利润
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[prices.length - 1][1]即最后一天不持有股票所获的最大利润
        return dp[prices.length - 1][1];
    }
}
