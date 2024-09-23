package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import javax.swing.plaf.synth.ColorType;

/**
 * ClassName: GreedyAlgorithm_8
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * @Author chen_sir
 * @Create 2024/5/11 10:56
 * @Version 1.0
 */
public class GreedyAlgorithm_8 {
    @Test
    public void test() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(new Solution8().canCompleteCircuit2(gas, cost));
    }
}

class Solution8 {
    // 暴力求解：遍历每一个加油站为起点的情况，模拟一圈。
    // 如果跑了一圈，中途没有断油，而且最后油量大于等于0，说明这个起点是ok的。
    // 关键在于如何模拟跑一圈：每次只记录当前剩余的油量，剩余油量大于0则继续跑，
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];// 记录剩余油量
            int index = (i + 1) % cost.length;
            while (rest > 0 && index != i) {// 未断油且未跑完一圈
                rest += gas[index] - cost[index];// 记录剩余油量
                index = (index + 1) % cost.length;
            }
            if (rest >= 0 && index == i) // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
                return i;
        }
        return -1;
    }


    // 首先如果总油量减去总消耗大于等于零那么一定可以跑完一圈，说明 各个站点的加油站 剩油量rest[i]相加一定是大于等于零的。
    // 记录每个站点的剩余油量，将剩余油量累加，如果剩余油量和为负，说明不能之前区间的任何位置都不能作为起点，到i这里会断油，那么起始位置从i+1算起，再从0计算curSum
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int start = 0;// 区间起点
        int curCount = 0;// 当前区间剩余油量总和
        int total = 0;// 一圈总共剩余油量之和
        for (int i = 0; i < gas.length; i++) {
            curCount += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (curCount < 0) {// 当前区间剩余油量之和<0，重新更新起始位置
                start = i + 1;
                curCount = 0;
            }
        }
        if (total < 0)// gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈
            return -1;
        return start;
    }
}
