package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.sql.Connection;
import java.util.*;

/**
 * ClassName: DynamicProgramming_13
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/8 15:27
 * @Version 1.0
 */
public class DynamicProgramming_13 {
    public static void main(String[] args) {
        new Solution13().change(5, new int[]{1, 2, 4});
    }
}

class Solution13 {
    /**
     * 动态规划：通过题意可知物品可无限次选取，故属于完全背包问题，对于一维dp数组，与01背包不同的是遍历背包重量是从前往后遍历的，后面的选取是在前面选取的基础上进行累计，
     * 以此来实现无限选取物品。对于二维dp数组，与01背包不同之处在于初始化方式不一样，只放入物品0的时候需要考虑一次放入多个物品0的情况，且状态转移方程为dp[i][j] = max(dp[i-1][j],dp[i][j-weight[i]]+value[i])，
     * 通过dp[i][j-weight[i]]来记录当前物品0~i的情况，以此来实现重复选取物品。
     * 确定dp数组及其下标含义：dp[i][j]表示从0~i个硬币中任意选取，放入总金额正好为j的背包的方法个数
     * 确定dp数组的递推公式：由于是求恰好放入背包的组合方法数，不是求最大金额数，因此dp[i][j] = dp[i-1][j] + dp[i][j-weight[i]]，其状态转移方程的含义及由来同目标和的题目
     * 确定dp数组初始化：由于物品可以无限次放入，故初始化方式和01背包问题大不相同，需要考虑能够重复放入物品0的情况
     * 确定dp数组遍历顺序：先遍历物品，后遍历背包
     * 时间复杂度：O(m*n)，空间复杂度：O(m*n)
     *
     * @param amount 总金额
     * @param coins  硬币数组
     * @return 凑成总金额的硬币组合个数
     */
    public int change(int amount, int[] coins) {
        //初始化dp数组
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            if (i >= coins[0] && i % coins[0] == 0)//i % coins[0] == 0是为了确保能够背包容量恰好能重复放入物品0
                dp[0][i] = 1;
        }
        //根据dp[i][]j的含义可知，j为0时的组合方法均为1，即不放入任何硬币
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }

        //先遍历物品，后遍历背包容量
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (j < coins[i])
                    dp[i][j] = dp[i - 1][j];
                else//求组合数，即dp相加
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
            }
        }

        Arrays.stream(dp).forEach(array-> System.out.println(Arrays.toString(array)));
        //dp[coins.length - 1][amount]即表示从0~coins.length - 1个硬币中任取任意数量的硬币，恰好组成金额为amount的组合数
        return dp[coins.length - 1][amount];
    }

    /**
     * 动态规划：可将二维数组压缩成一维数组，对于一维dp数组，与01背包不同的是遍历背包重量是从前往后遍历的，后面的选取是在前面选取的基础上进行累计，
     * 以此来实现无限选取物品。其他步骤同01背包问题
     * 时间复杂度：O(m*n)，空间复杂度：O(m)
     *
     * @param amount 总金额
     * @param coins  硬币数组
     * @return 凑成总金额的硬币组合个数
     */
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            //从后往前遍历：01背包问题，不可重复选取
//            for (int j = amount; j >= coins[i]; j--) {
//                dp[j] = dp[j] + dp[j - coins[i]];
//            }
            //从前往后：完全背包问题，可无限次选取
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        //dp[amount]即表示从0~coins.length - 1个硬币中任取任意数量的硬币，恰好组成金额为amount的组合数
        return dp[amount];
    }

    //单条路径记录集合
    List<Integer> path = new ArrayList<>();
    //所有路径记录总和
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 法三：回溯法，同组合问题，查找组合为target的所有组合的方法，要求元素可重复选择
     * 时间复杂度: O(n * 2^n)，空间复杂度：O(amount)
     *
     * @param amount 总金额
     * @param coins  硬币数组
     * @return 凑成总金额的硬币组合个数
     */
    public int change3(int amount, int[] coins) {
        //升序排序，便于后续剪枝操作
        Arrays.sort(coins);
        BackTraceFindChange(amount, coins, 0, 0);
        //遍历结果集，验证结果是否满足预期
        result.forEach(list ->
                System.out.println(list.toString())
        );
        //结果集中路径的个数即组合方法数
        return result.size();
    }

    public void BackTraceFindChange(int amount, int[] coins, int sum, int startIndex) {
        if (sum == amount) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < coins.length && sum + coins[i] <= amount; i++) {
            sum += coins[i];
            path.add(coins[i]);
            BackTraceFindChange(amount, coins, sum, i);
            sum -= coins[i];
            path.remove(path.size() - 1);
        }
    }
}
