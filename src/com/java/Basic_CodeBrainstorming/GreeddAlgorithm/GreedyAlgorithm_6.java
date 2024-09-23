package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_6
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * <p>
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * <p>
 * 返回 你能获得的 最大 利润 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 *
 * @Author chen_sir
 * @Create 2024/5/9 10:41
 * @Version 1.0
 */
public class GreedyAlgorithm_6 {
    @Test
    public void test() {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(new Solution6().maxProfit(prices));
    }
}

class Solution6 {
    //总利益可以把每天的利益分解然后求和，局部最优：收集每天的正利润，全局最优：求得最大利润。
    //时间复杂度：O(n)
    //空间复杂度：O(1)
    public int maxProfit(int[] prices) {
        int balance = 0;
        for (int i = 1; i < prices.length; i++) {
            balance += Math.max(prices[i] - prices[i - 1], 0);// 当前股票每天的利润为正，收集
        }
        return balance;
    }
}
