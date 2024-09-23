package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * ClassName: DynamicProgramming_11
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/5 16:10
 * @Version 1.0
 */
public class DynamicProgramming_11 {
    public static void main(String[] args) {
        System.out.println(new Solution11().findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3));
    }
}

class Solution11 {
    //记录符合条件的情况
    List<Integer> path = new ArrayList<Integer>();
    //所有满足条件的情况总和
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 回溯法：本题要求表达式结果为target，且每个数字直接只能用+和-，因此一定有left组合 - right组合 = target，且left组合 + right组合 = sum，因此可以推导出left = (target + sum) / 2，又因为target和sum是给出的，因此可以计算出left的和
     * 因此本题可以转换为找出和为left的数字组合个数，每个数字只能取一次，不能重复，和给出一个数组，求满足目标总和的所有数组（可重复选取元素）的题目一样，使用回溯法即可
     * 确定回溯函数的参数：待查找数组、目标和、当前路径总和、startIndex（用于记录处理到哪一个元素了，防止重复遍历）
     * 确定终止条件：当前路径总和等于目标和，将该路径记录到result总记录中
     * 确定单层搜索过程：遍历当前层每一个结点之和，判断是否等于目标和
     * 时间复杂度：时间复杂度: O(n * 2^n)，空间复杂度：O(target)
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int left = 0;
        //计算数组元素总和
        int sum = Arrays.stream(nums).sum();

        //边界情况处理
        if (target > sum) return 0;
        if ((target + sum) % 2 != 0) return 0;
        //计算出left和
        left = (target + sum) / 2;

        //数组排序，便于剪枝操作
        Arrays.sort(nums);
        //回溯
        BackTrace(nums, left, 0, 0);
        //满足和为left的路径数量即为结果
        return result.size();
    }

    public void BackTrace(int[] nums, int target, int sum, int startIndex) {
        //当前路径之和等于target
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < nums.length && sum + nums[i] <= target; i++) {//sum + nums[i] <= target为剪枝操作
            sum += nums[i];
            path.add(nums[i]);
            BackTrace(nums, target, sum, i + 1);//i+1实现不重复选取元素
            sum -= nums[i];
            path.remove(path.size() - 1);//回溯list的最后一个元素，即path.size()-1下标的元素
        }
    }

    /**
     * 动态规划：通过法一的分析，可以将此题转化为寻找和为left的组合序列个数，而又因为每个数字只能选取一次，且最大容量为left，因此也可转化为01背包问题，不过不同于传统01背包问题的dp[i][j]表示任取0~i中的物品，放入容量为j的背包中的最大价值，
     * 而本题中是求和为left的不同组合的个数，因此dp[i][j]表示从数字0~i中，任取数字，可正好组成和为j的组合个数
     * 确定dp数组及其下标定义：dp[i][j]表示从物品0~i中，取任意个物品（不可重复选取），可正好组成和为j的组合个数
     * 确定dp数组初始化：dp[0][0]指的是物品0的大小组合成和为0的组合个数，即不放入物品，个数为0：dp[0][0]为1，且若if (nums[0] == i)即正好能将物品0放入，有一个方法：dp[0][nums[0]] = 1
     * 确定dp数组递推公式：dp[i][j]有两个方向得来，dp[i-1][j]和dp[i-1][j-nums[i]]，dp[i-1][j]到dp[i][j]是指不放入物品i已经正好组合成和为j的组合，而dp[i-1][j-nums[i]]指放入物品i正好组成和为j的组合个数，而j-nums[i]指的是没放入物品i之前的组合个数
     * 确定dp数组遍历方式：二维数组，因此顺序可替换，先遍历物品，后便利背包容量，背包容量从前往后遍历
     * 时间复杂度：O(m*n)，空间复杂度：O(m*n)
     *
     * @param nums   待查找数组
     * @param target 代求的目标和
     * @return 和为target的组合个数
     */
    public int findTargetSumWays2(int[] nums, int target) {
        //nums数组求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        //边界条件判断
        if (Math.abs(target) > sum) return 0;
        if ((target + sum) % 2 != 0) return 0;

        //根据题意将问题转化为求和为left的组合个数
        int left = 0;
        //计算出left和
        left = (target + sum) / 2;

        //dp数组定义及初始化
        int[][] dp = new int[nums.length][left + 1];
        dp[0][0] = 1;
        for (int i = 0; i < left + 1; i++) {
            if (nums[0] == i)
                dp[0][nums[0]] = 1;
        }

        //遍历dp数组，先遍历物品，后遍历重量
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < left + 1; j++) {
                if (j < nums[i]) dp[i][j] = dp[i - 1][j];//物品大小大于j，无法放入
                else//能够放入
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
            }
        }
        Arrays.stream(dp).forEach(array-> System.out.println(Arrays.toString(array)));
        //从所有物品中任取物品组合成和为left的组合个数即为结果
        return dp[nums.length - 1][left];
    }

    /**
     * 动态规划：同二维转一维的原理，将dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]转为dp[j] = dp[j] + dp[j-nums[i]]，dp[j]表示任取0~i的物品，其和为j的组合个数，dp[0]表示和为0的组合个数，只有一种就是不取，故dp[0] = 1
     *
     * @param nums   待查找数组
     * @param target 代求的目标和
     * @return 和为target的组合个数
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int left = 0, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum) return 0;
        if ((target + sum) % 2 != 0) return 0;

        //计算出left和
        left = (target + sum) / 2;

        //dp数组初始化
        int[] dp = new int[left + 1];
        dp[0] = 1;

        //必须先遍历物品，后遍历背包大小
        for (int i = 0; i < nums.length; i++) {
            //背包大小从后往前遍历
            for (int j = left; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        //dp[left]即从所有物品中任取，正好组成和为left的个数
        return dp[left];
    }

}
