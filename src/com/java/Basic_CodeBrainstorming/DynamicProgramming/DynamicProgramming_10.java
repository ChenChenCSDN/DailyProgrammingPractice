package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_10
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/4 21:57
 * @Version 1.0
 */
public class DynamicProgramming_10 {
    public static void main(String[] args) {
        new Solution10().lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1});
    }
}

class Solution10 {
    /**
     * 动态规划：本题的思路是两两石头进行碰撞，使得最后碰撞完的石头重量最小，因为碰撞的结果是两两石头的重量相减，因此可以根据分配律法则归纳成两堆重量相近的石头进行碰撞，碰撞完的结果就是最小的结果，因此可将此题转化为容量最大为sum/2的背包，
     * 计算从石头堆里每次任选一个石头放入背包中，当背包容量达到sum/2时其包内的最大重量为多少（最好的情况正好为sum的一半，则两堆石头平分重量，碰撞完结果为0)，因为石头的重量也是其自身的价值，因此类比01背包的dp[j]表示容量为j的背包所能装的最大价值，
     * 此题dp[j]表示重量为j的背包所能装的最大重量
     * 确定dp数组及其下标含义：dp[j]表示重量为j的背包所能装的最大重量（因此石头的重量和价值相同，最大价值可以类比为最大重量）
     * 确定dp递推公式：同01背包问题，判断放与不放问题，即dp[j] = max(dp[j],dp[j-weight[i]] + value[i])
     * 确定dp数组初始化：因为dp数组是一维的，且背包重量从后往前遍历，因此初始化为0即可
     * 确定dp遍历顺序：一维dp数组先遍历物品，后遍历背包重量（从后往前遍历）
     * 时间复杂度：O(m*n)，空间复杂度：O(m)，m是石头总重量（准确的说是总重量的一半），n为石头块数
     *
     * @param stones 所要计算的石头堆
     * @return 碰撞完后的最小重量
     */
    public int lastStoneWeightII(int[] stones) {
        //dp数组初始化为0
        int[] dp = new int[1501];

        //计算stones物品的总重量
        int sum = Arrays.stream(stones).sum();

        int target = sum / 2;
        //先遍历物品，后遍历背包容量
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        //遍历dp数组，验证是否符合预期
        System.out.println(Arrays.toString(dp));

        //dp[target]指背包容量为target时背包所能装的最大重量，sum-dp[j]是另一堆的最大重量，相减即是重量之差
        return sum - dp[target] - dp[target];
    }
}
