package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_10
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：bills = [5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 *
 * @Author chen_sir
 * @Create 2024/5/13 10:24
 * @Version 1.0
 */
public class GreedyAlgorithm_10 {
    @Test
    public void test() {
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println(new Solution10().lemonadeChange(bills));
    }
}

class Solution10 {
    //分析题目，发现判断空间很少，有如下三种情况
    //有如下三种情况：
    //情况一：账单是5，直接收下。
    //情况二：账单是10，消耗一个5，增加一个10
    //情况三：账单是20，优先消耗一个10和一个5，如果不够，再消耗三个5
    //时间复杂度：O(n)
    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0, tenCount = 0, tweenCount = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) { //情况一：账单为5
                fiveCount++;//直接收下
            } else if (bills[i] == 10) {//情况二：账单为10
                fiveCount--;//消耗一个5
                tenCount++;//增加一个10
            } else {//情况三：账单为20
                if (tenCount > 0 && fiveCount > 0) {//优先消耗一个10和一个5，增加一个20
                    fiveCount--;
                    tenCount--;
                    tweenCount++;
                } else {//消耗三个5，增加一个20
                    fiveCount -= 3;
                    tweenCount++;
                }
            }
            if (fiveCount < 0 || tenCount < 0 | tweenCount < 0)//金额数量为负，说明不能找零
                return false;
        }
        return true;
    }
}

