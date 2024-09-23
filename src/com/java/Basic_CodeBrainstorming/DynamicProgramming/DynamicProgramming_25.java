package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * ClassName: DynamicProgramming_25
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/18 9:57
 * @Version 1.0
 */
public class DynamicProgramming_25 {
    public static void main(String[] args) {
        new Solution25().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});
    }
}

class Solution25 {
    /**
     * 动态规划：本题同动态规划：123.买卖股票的最佳时机III这道题，状态转移方程相同，区别在于本题对于买卖的次数有限制，最多买卖k次，当k=2时即为上一道题的答案，状态转移方程的含义为：0-表示第一次持有股票，1-表示第一次不持有股票，2-表示第二次持有股票，3-表示第二次不持有股票；
     * 当k=3时，状态转移方程的含义为：0-表示第一次持有股票，1-表示第一次不持有股票，2-表示第二次持有股票，3-表示第二次不持有股票，4-表示第三次持有股票，5-表示第三次不持有股票，故一共有0~2*k-1个状态(包括)，通过分析可以看出为偶数时表示持有股票的状态，为奇数时表示不持有股票的状态。
     * 确定dp数组及其下标的含义：dp[i][j]表示第i天状态为j的情况下所能获得的最大利润
     * 确定dp数组递推公式：因为最多可以买卖k次，则j的范围是从0~2*k-1，dp[i][偶数]为持有股票的状态，dp[i][奇数]为不持有股票的状态，因此在遍历天数时，要判断k的下标，然后对其进行相应的操作。
     * 确定dp数组初始化：当i为0时，表示第0天的时候，则j为偶数一定是-prices[0]，为奇数一定为0
     * 确定dp数组遍历顺序：从前往后遍历
     *
     * @param k      最大买卖次数
     * @param prices 股票价格数组
     * @return 能获取的最大利润
     */
    public int maxProfit(int k, int[] prices) {
        //定义dp数组
        int[][] dp = new int[prices.length][2 * k];//2 * k用于记录0~2*k-1个状态
        //初始化dp数组
        for (int i = 0; i < 2 * k; i++) {
            if (i % 2 == 0)
                dp[0][i] = -prices[0];
        }
        //遍历dp数组
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k; j++) {
                //状态为偶数，说明是持有股票的状态
                if (j % 2 == 0) {
                    //当j为0时表示第一次持有股票的情况，这种情况下只能购买当前股票
                    if (j == 0)
                        dp[i][j] = Math.max(dp[i - 1][j], -prices[i]);
                        //当j不为0说明是第j次持有股票（非第一次），则其最大利润为只有不持有股票的最大利润-买入当前股票的价格
                    else
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }
                //状态为奇数，说明是不持有股票的状态
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
        }
        //打印dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(array -> System.out.println(Arrays.toString(array)));

        //dp[prices.length - 1][2 * k - 1]即最后一天第k次不持有股票所获的最大利润，由于第k次不持有股票的最大利润覆盖了第k次持有股票的最大利润的情况，故返回第k次不持有股票的最大利润
        return dp[prices.length - 1][2 * k - 1];
    }
}
