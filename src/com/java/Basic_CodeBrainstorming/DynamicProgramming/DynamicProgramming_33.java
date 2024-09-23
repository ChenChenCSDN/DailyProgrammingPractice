package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_33
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/22 19:38
 * @Version 1.0
 */
public class DynamicProgramming_33 {
    public static void main(String[] args) {
        new Solution33().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }
}

class Solution33 {
    /**
     * 贪心算法：贪心算法贪的地方在于当前和为负数时，一定会拉低整体和的大小，要立刻放弃，从下一个元素开始计算和。
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     * 全局最优：选取最大“连续和”
     * 局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。
     * 时间复杂度为：O(n)，空间复杂度：O(n)
     *
     * @param nums 待查找数组
     * @return 最大子数组和
     */
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        //记录当前子数组的和
        int count = 0;
        //记录最大子数组的和
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            result = Math.max(result, count);
            //当前和为负数，会拉低整体和的大小，故舍去
            if (count < 0)
                count = 0;
        }
        return result;
    }

    /**
     * 动态规划：
     * 确定dp数组及其下标含义：dp[i]代表以nums[i]为结尾(包括下标i)的最大连续子序列和.
     * 确定dp数组递推公式：根据dp[i]的定义，可知dp[i]可由两个方向推导而来，dp[i-1]+nums[i]，即：nums[i]加入当前连续子序列和，另一个是nums[i]，即：从头开始计算当前连续子序列和。又因为取最大值，故dp[i] = max{dp[i-1]+nums[i],nums[i]}.
     * 确定dp数组初始化：dp[0]=nums[0]
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * @param nums 待查找数组
     * @return 最大子数组和
     */
    public int maxSubArray(int[] nums) {
        //边界条件
        if (nums.length == 1)
            return nums[0];

        //初始化dp数组
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        //记录子序列和的最大值
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + dp[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));
        return result;
    }

    //动态规划的第二种写法：dp[i - 1] + dp[i] > dp[i]即当加入当前元素到子序列中的和大于不加的和，故将其纳入子序列中。
    public int maxSubArray3(int[] nums) {
        //边界条件
        if (nums.length == 1)
            return nums[0];
        int[] dp = Arrays.copyOf(nums, nums.length);
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] + dp[i] > dp[i])
                dp[i] = dp[i - 1] + dp[i];
            result = Math.max(result, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return 0;
    }
}
