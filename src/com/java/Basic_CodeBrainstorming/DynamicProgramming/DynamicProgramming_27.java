package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_27
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/19 9:56
 * @Version 1.0
 */
public class DynamicProgramming_27 {
    public static void main(String[] args) {
        new Solution27().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
    }
}

class Solution27 {
    /**
     * 动态规划：本题思路同动态规划：122.买卖股票的最佳时机II题，即可以无限次购买股票，但此题加了一个卖出股票需要支付手续费这个条件，其余情况相同。有手续费意味着在卖出股票的时候，还需要支出手续费，其他分析结果和之前的相同。
     * 确定dp数组下标及其含义：dp[i][0]表示第i天持有股票的最大利润，dp[i][1]表示第i天不持有股票的最大利润。
     * 确定dp数组递推公式：持有股票的情况和之前一样：dp[i][0] = max{dp[i-1][0],dp[i-1][1]-prices[i]}，而不持有股票的情况下，如果是要卖出股票，则还需要支付对应的手续费：dp[i][1] = max{dp[i-1][1].dp[i-1][0]+prices[i]-free};
     * 确定dp数组初始化：dp[0][0] = -prices[0]，dp[0][1] = 0
     * 时间复杂度：O(n)，空间复杂度：O(n*2)
     *
     * @param prices 股票价格数组
     * @param fee    手续费
     * @return 能够获取的最大利润
     */
    public int maxProfit(int[] prices, int fee) {
        //定义并初始化dp数组
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        //从前往后遍历dp数组
        for (int i = 1; i < prices.length; i++) {
            //持有股票的情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            //不持有股票的情况
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);//dp[i - 1][0] + prices[i] - fee是指卖出股票的情况，还需要支付额外的手续费即减去free
        }

        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[prices.length-1][1]即最后一天不持有股票所获的最大利润，不持有股票的最大利润覆盖了持有股票的最大利润的情况，故返回不持有股票的最大利润
        return dp[prices.length - 1][1];
    }
}
