package com.java.Basic_algorithms.Array;

import org.junit.Test;

/**
 * ClassName: Array_2
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 * @Author chen_sir
 * @Create 2023/11/13 14:51
 * @Version 1.0
 */
public class Array_2 {
    @Test
    public void test(){
//        int[] prices = new int[]{1,2,3,4,5};
//        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices = new int[]{5,8,1,4,7,9};

        System.out.println(maxBalance.maxProfit(prices));
    }
}
class maxBalance {
    /*
    * 解题关键：
    *    股票每天的价格已在数组中，按贪心算法的策略只需在股票价格最低的时候买入，股票即将下降的时候卖出即可，然后局部最优解整体求和就行！
    * */
    public static int maxProfit(int[] prices) {
        int total=0;//利益总和
        for (int i = 0; i < prices.length - 1; i++) {   //遍历数组
            while(i<prices.length-1&&prices[i] >= prices[i+1]){ //找到股票开始涨的那一天
                i++;
            }
            int min=prices[i];  //股票开始涨的最小值
            while(i<prices.length-1&&prices[i]<=prices[i+1]){   //找到股票开始降的那一天
                i++;
            }
            total+=prices[i] - min; //累计利润：股票降的前一天（卖价最大）- 股票开始涨的那一天，累加求和
        }
        return total;
    }


    //优化：
    public static int maxProfit2(int[] prices) {
        int total = 0;//盈利总和
        int i=0;
        while(i<prices.length - 1){
            total+=Math.max(prices[i+1]-prices[i],0);   //股价上升，则计算盈利，记入总和中
            i++;
        }
        return total;
    }
    //标准答案：
//    public int maxProfit(int[] prices) {
//        if (prices == null || prices.length < 2)
//            return 0;
//        int total = 0, index = 0, length = prices.length;
//        while (index < length) {
//            //如果股票下跌就一直找，直到找到股票开始上涨为止
//            while (index < length - 1 && prices[index] >= prices[index + 1])
//                index++;
//            //股票上涨开始的值，也就是这段时间上涨的最小值
//            int min = prices[index];
//            //一直找到股票上涨的最大值为止
//            while (index < length - 1 && prices[index] <= prices[index + 1])
//                index++;
//            //计算这段上涨时间的差值，然后累加
//            total += prices[index++] - min;
//        }
//        return total;
//    }
}