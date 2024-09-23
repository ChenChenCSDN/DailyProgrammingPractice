package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_24
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/17 17:18
 * @Version 1.0
 */
public class DynamicProgramming_24 {
    public static void main(String[] args) {
        new Solution24().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
    }
}

class Solution24 {
    /**
     * 动态规划：本题与之前的两道买卖股票问题都不相同，因为最多只能买入卖出两次，因此需要考虑第一次持有股票、第一次不持有股票、第二次持有股票和第二次不持有股票这四种情况，则相比于之前状态转移方程只考虑当前股票是否持有和不持有的情况，需要四种状态来记录。
     * 确定dp数组及其下标含义：dp[i][j]表示第i天状态j情况下所能获得的最大利润
     * 确定dp数组递推公式：因为需要考虑第一次持有股票、第一次不持有股票、第二次持有股票和第二次不持有股票这四种情况，因此需要四个状态来记录对应的情况，0 - 第一次持有股票，1 - 第一次不持有股票，2 - 第二次持有股票，3 - 第二次不持有股票
     * dp[i][0]表示第i天第一次持有股票所获得的最大利润，其余下标情况类似。dp[i][0] = max{dp[i-1][0],-prices[i]}，dp[i][1] = max{dp[i-1][1],dp[i-1][0] + prices[i]}，dp[i][2] = max{dp[i-1][2],dp[i-1][1]-prices[i]}，dp[i][3] = max{dp[i-1][3],dp[i-1][2]+prices[i]};
     * 确定dp数组初始化：dp[0][0]表示第0天第一次持有股票，即购买股票：dp[0][0] = -prices[0]，dp[0][1]表示第0天第一次不持有股票，而第一天不持有股票就是不购买股票：dp[0][1] = 0，dp[0][2]表示第0天第二次持有股票，第二次持有股票是通过第一次买入和卖出股票后来得到的，相当于第0天买入股票后立即卖出，
     * 然后再买入一次股票，则dp[0][2] = -prices[0]，dp[0][3]表示第0天第二次不持有股票，相当于第一次买股票后卖出，然后第二次买股票后再卖出，此时的最大利润肯定为0，故dp[0][3] = 0
     * 确定dp数组遍历方式：从前往后遍历
     * 时间复杂度：O(n)，空间复杂度：O(n*5)
     *
     * @param prices 股票价格数组
     * @return 能获取的最大利润
     */
    public int maxProfit(int[] prices) {
        //dp数组初始化
        int[][] dp = new int[prices.length][4];
        //dp[0][0]表示第0天第一次持有股票，即购买股票：dp[0][0] = -prices[0]
        dp[0][0] = -prices[0];
        //dp[0][1]表示第0天第一次不持有股票，而第一天不持有股票就是不购买股票：dp[0][1] = 0
        dp[0][1] = 0;
        //dp[0][2]表示第0天第二次持有股票，第二次持有股票是通过第一次买入和卖出股票后来得到的，相当于第0天买入股票后立即卖出，然后再买入一次股票，则dp[0][2] = -prices[0]
        dp[0][2] = -prices[0];
        //dp[0][3]表示第0天第二次不持有股票，相当于第一次买股票后卖出，然后第二次买股票后再卖出，此时的最大利润肯定为0，故dp[0][3] = 0
        dp[0][3] = 0;

        //从前往后遍历dp数组
        for (int i = 1; i < prices.length; i++) {
            //第一次持有股票的最大利润
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            //第一次不持有股票的最大利润
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            //第二次持有股票的最大利润
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            //第二次不持有股票的最大利润
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }

        //打印dp数组，验证是否符合预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[prices.length-1][3]即最后一天第二次不持有股票所获的最大利润，由于第二次不持有股票的最大利润覆盖了第二次持有股票的最大利润的情况，故返回第二次不持有股票的最大利润
        return dp[prices.length - 1][3];
    }
}
