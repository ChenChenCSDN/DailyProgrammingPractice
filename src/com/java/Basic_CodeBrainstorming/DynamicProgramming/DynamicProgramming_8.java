package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_8
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/1 11:36
 * @Version 1.0
 */
public class DynamicProgramming_8 {
    public static void main(String[] args) {
        int[] weight = {5, 5, 3, 5, 5, 2};
        int[] value = {2, 3, 1, 5, 4, 3};
        Solution8.axBig3(weight, value, 5);
    }
}

class Solution8 {
    /**
     * 动态规划：法一，dp数组使用二维数组
     * 确定dp数组及其下标含义：dp[i][j]表示从0~i的物品中任取放进容量为j的背包中的价值最大总和
     * 确定dp数组的递推式：dp[i][j]根据其含义，可以得出有两个方向得出：dp[i-1][j]和dp[i-1][j-weight[i]]，dp[i-1][j]到dp[i][j]表示当前背包容量放不下物品i，dp[i][j]的最大值就是dp[i-1][j]
     * 而dp[i-1][j-weight[i]]表示当前背包容量能够放入物品i，因此其价值总和为dp[i-1][j-weight[i]] + value[i]
     * 确定dp数组初始化：当i为0时，表示只放物品0，则当j大于物品0的重量weight[0]后其dp[0][j]为value[0]
     * 确定dp数组遍历顺序：因为背包容量从小到大，因此从前往后遍历，其次要同时遍历背包和物品，可先遍历物品，依次增大背包容量，更好理解
     *
     * @param weight  物品对应的重量
     * @param value   物品对应的价值
     * @param bagSize 背包的最大容量
     */
    public static void axBig(int[] weight, int[] value, int bagSize) {
        //创建dp数组
        int goodsNumber = weight.length;
        int[][] dp = new int[goodsNumber][bagSize + 1];
        //dp数组初始化
        for (int i = 0; i < bagSize + 1; i++) {
            if (i >= weight[0]) dp[0][i] = value[0];
        }
        //先遍历物品，再遍历背包容量
        for (int i = 1; i < goodsNumber; i++) {
            for (int j = 0; j < bagSize + 1; j++) {
                //当前背包容量可以放下物品i
                if (j - weight[i] >= 0)
                /**
                 * 当前背包的容量可以放下物品i
                 * 那么此时分两种情况：
                 *    1、不放物品i
                 *    2、放物品i
                 * 比较这两种情况下，哪种背包中物品的最大价值最大
                 * dp[i - 1][j - weight[i]]表示不放物品i是背包内的最大价值
                 */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                    //当前背包容量可以放不下物品i
                else
                /**
                 * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                 * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                 */
                    dp[i][j] = dp[i - 1][j];
            }
        }
        //遍历dp数组，验证是否满足预期
        Arrays.stream(dp).forEach(a -> {
            System.out.println(Arrays.toString(a));
        });
    }

    /**
     * 动态规划：法二：dp数组使用一维数组
     * 通过观察二维dp数组的定义，及其递推公式：dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);可以看出dp[i][j]要么不放入当前物品，要么
     * 判断放入该物品后当前背包的最大价值是多少，因此只需计算不放当前物品的情况下的背包最大值 （dp[i - 1][j - weight[i]]） + 当前物品情况即可，因此当前状态只与上一个物品的
     * 背包状态有关，故可以使用滚动数组，来记录上一层数组的情况并覆盖即可。
     * 另外一维数组遍历方式只能是先遍历物品，后遍历背包，否则会导致一直在处理同一个位置的情况，此外遍历背包只能从后往前遍历，
     * 因此当前是否放入物品由之前背包内个情况决定，如果从前往后遍历，会导致之前背包的情况被覆盖，因此只能从后往前遍历。
     * 从前往后遍历相当于每次之前放的物品后面每次也会放进入（第一次放入物品0，第二次放入物品0、物品1，第三次在第二次的基础上再放入物品2
     * 从后往前遍历相当于每个物品只放入一次
     *
     * @param weight  物品对应的重量
     * @param value   物品对应的价值
     * @param bagSize 背包能装入物品的最大价值
     */
    public static void axBig2(int[] weight, int[] value, int bagSize) {
        int goodsNumber = weight.length;
        int[] dp = new int[bagSize + 1];
        //遍历dp数组
        //先遍历物品
        for (int i = 0; i < goodsNumber; i++) {
            //后遍历背包大小
            for (int j = bagSize; j >= weight[i]; j--)
//                if(j>=weight[i])
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        }
        System.out.println(Arrays.toString(dp));
    }


    static int maxValue;

    /**
     * 回溯法：遍历所有的情况，记录最大值
     * 确定递归函数的参数：物品重量数组、物品价值数组、背包容量、当前背包内的容量、当前背包的最大价值、index（用于记录遍历到哪个背包了）
     * 确定终止条件：当前背包容量 > 背包的容量
     * 确定单层搜索过程：记录当前背包的容量，当前背包的最大价值
     * 时间复杂度：O(2^n)
     *
     * @param weight
     * @param value
     * @param bagSize
     */
    public static void axBig3(int[] weight, int[] value, int bagSize) {
        findBagValue(weight, value, 0, 0, 0, bagSize);
        System.out.println(maxValue);
    }

    public static void findBagValue(int[] weight, int[] value, int index, int currentValue, int currentSize, int bagSize) {
        //终止条件：当前背包容量大于背包的最大容量
        if (currentSize > bagSize)
            return;
        else
            //记录背包可存放物品的最大值
            maxValue = Math.max(currentValue, maxValue);
        for (int i = index; i < weight.length; i++) {
            //记录当前背包的放的容量和价值
            currentValue += value[i];
            currentSize += weight[i];
            findBagValue(weight, value, i + 1, currentValue, currentSize, bagSize);
            //回溯
            currentValue -= value[i];
            currentSize -= weight[i];
        }
    }
}
