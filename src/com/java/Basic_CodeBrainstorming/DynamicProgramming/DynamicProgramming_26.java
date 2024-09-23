package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_26
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/18 15:32
 * @Version 1.0
 */
public class DynamicProgramming_26 {
    public static void main(String[] args) {

    }
}

class Solution26 {
    /**
     * 动态规划：本题思路同动态规划：122.买卖股票的最佳时机II题，但是区别在于本题有冷冻期限制，即前一天卖出股票后，当天处于冷冻期不能买入股票，其他情况一样。而冷冻期相当于不操作，只是限制了不能买，但其当前获得的利润是不变的，因此跳过该天也是可以的。
     * 确定dp数组及其下标含义：dp[i][0]表示第i天持有（不等于买入）股票所得的最大利润，dp[i][1]表示第i天不持有（不等于卖出）股票所得的最大利润。
     * 确定dp数组递推公式：dp[i][0]可由两种情况得到：第i-1天就持有股票，所得利润就是i-1天持有股票的利润，即dp[i-1][0]，另一种情况是第i天购入股票，而第i天购入股票，如何前一天为冷冻期则无法购买股票，因此需要在第i-2天来够买股票，第i-2天就包括了已经过了冷冻期的情况，故所得利润为dp[i-2][1]-prices[i]，dp[i][1]也可由两种情况得到：第i-1天就不持有股票，所得利润就是i-1天不持有股票：dp[i-1][1]，
     * 另一种情况是第i天卖出股票，不持有股票的利润为第i-1天持有股票的利润+当天股票的价格prices[i]：dp[i][1] = dp[i-1][0] + prices[i]，又因为是要求最大利润，故取最大值：dp[i][0] = max{dp[i-1][0]，dp[i-2][1]-prices[i]}，dp[i][1] = {dp[i-1][1]，dp[i-1][0] + prices[i]}
     * 确定dp数组初始化：dp[0][0]表示第0天持有股票的最大利润，故dp[0][0]=-prices[0]，dp[0][1]表示第0天不持有股票的最大利润，为0，而dp[1][0]表示第1天持有股票的状态，dp[1][0]=max{-prices[0],-prices[1]}，dp[1][1]表示第1天不持有股票的状态
     * 时间复杂度：O(n)，空间复杂度：O(n*2)
     *
     * @param prices 股票价格数组
     * @return 能获取的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;
        //初始化dp数组
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[1][0] = Math.max(-prices[0], -prices[1]);
        dp[1][1] = Math.max(0, -prices[0] + prices[1]);
        //遍历dp数组，从前往后遍历
        for (int i = 2; i < prices.length; i++) {
            //今天才持有，昨天不持有，前天不持有(√)
            //今天才持有，昨天不持有，前天持有(×不可能有这种情况，没有冷冻)
            //以前持有，昨天肯定持有(√)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);

            //今天才不持有，昨天持有(√)
            //以前就不持有，昨天一定不持有(√)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        //dp[prices.length-1][1]即最后一天不持有股票所获的最大利润，不持有股票的最大利润覆盖了持有股票的最大利润的情况，故返回不持有股票的最大利润
        return dp[prices.length - 1][1];
    }
}
