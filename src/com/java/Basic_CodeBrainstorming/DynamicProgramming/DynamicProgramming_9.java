package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * ClassName: DynamicProgramming_9
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/2 15:06
 * @Version 1.0
 */
public class DynamicProgramming_9 {
    public static void main(String[] args) {
        System.out.println(new Solution9().canPartition3(new int[]{1, 1, 1, 1}));
    }
}

class Solution9 {
    /**
     * 法一：回溯法
     * 确定递归函数参数：nums数组、nums数组总和的一半、index（不重复选取）、当前子集的和
     * 确定终止条件：当前子集的和=nums数组总和的一半
     * 确定单纯搜索过程：记录当前子集的总和
     *
     * @param nums 待判断的数组
     * @return true - 可以分割  false - 不可分割
     */
    public boolean canPartition(int[] nums) {
        //计算当前数组的总和
        int sum = Arrays.stream(nums).sum();
        //数组总和不能平分，一定不能分割
        if (sum % 2 != 0)
            return false;

        //nums数组排序，用于剪枝
        Arrays.sort(nums);

        int target = sum / 2;
        //回溯递归
        return BackTrace(nums, target, 0, 0);
    }

    public boolean BackTrace(int[] nums, int sum, int cur, int startIndex) {
        if (cur == sum)
            return true;
        for (int i = startIndex; i < nums.length && cur + nums[i] <= sum; i++) {//cur + nums[i] <= sum为剪枝操作，前提是nums有序
            cur += nums[i];
            if (BackTrace(nums, sum, cur, i + 1))
                return true;
            cur -= nums[i];
        }
        return false;
    }

    /**
     * 动态规划：01背包问题的本质是给定一个背包的最大容量，物品的重量及其对应价值，每个物品只能拿一次，计算出对应背包容量可存放的最大价值，而此题的本质是计算数组中的部分元素之和是否等于整个数组之和的一般，若满足则说明存在可拆分的子集，
     * 每个数组元素只能拿一次，因此可以转换为背包容量为sum/2的01背包问题，判断容量为sum/2的背包所存放的数组最大值是否等于sum/2，因为背包的容量是根据数组之和来得出的，因此每个数组的重量也是其自身的元素值，且背包的价值是根据数组的值得来的，故
     * 数组的价值也是其自身，其他求解过程同01背包问题。
     * 使用一维滚动数组降低空间复杂度，注意需要先遍历物品，后遍历背包容量，且背包容量从后往前遍历
     * 时间复杂度：O(n^2)，空间复杂度：O(n)
     *
     * @param nums 待判断的数组
     * @return true - 能分割成两个和相同的子集 flase - 不能分割
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        //计算当前数组的总和
        for (int num : nums) {
            sum += num;
        }
        //数组总和不能平分，一定不能分割
        if (sum % 2 != 0)
            return false;
        //背包的最大容量为数组之和的一半
        int target = sum / 2;
        int[] dp = new int[target + 1];
        //先遍历物品，后遍历背包容量
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
        //如果背包容量为sum/2能装的最大值恰好等于sum/2，说明可以分割
        return dp[target] == target;
    }

    /**
     * 动态规划：思路和方法同上面，区别是dp数组使用二维数组
     * 时间复杂度：O(n^2)，空间复杂度：O(n^2)
     *
     * @param nums 待判断的数组
     * @return true - 能分割成两个和相同的子集 flase - 不能分割
     */
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        //计算当前数组的总和
        for (int num : nums) {
            sum += num;
        }
        //数组总和不能平分，一定不能分割
        if (sum % 2 != 0)
            return false;
        //背包的最大容量为数组之和的一半
        int target = sum / 2;
        int[][] dp = new int[nums.length + 1][target + 1];
        //1, 2, 3, 5, 3
        //dp数组初始化
        //只放入物品0的情况下
        for (int i = 0; i < target + 1; i++) {
            if (i >= nums[0])
                dp[0][i] = nums[0];
        }
        //遍历dp数组，先遍历物品，后遍历背包容量
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                //判断能否放下物品i
                if (j >= nums[i])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        //遍历dp数组，验证是否满足预期
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        //背包容量为target时背包的最大容量为target时说明能分割
        return dp[nums.length - 1][target] == target;
    }
}
