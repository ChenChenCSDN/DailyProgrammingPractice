package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import java.util.Arrays;

/**
 * ClassName: DynamicProgramming_29
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/19 17:09
 * @Version 1.0
 */
public class DynamicProgramming_29 {
    public static void main(String[] args) {
        new Solution29().findLengthOfLCIS(new int[]{1, 3, 5, 4, 2, 3, 4, 5});
    }
}

class Solution29 {
    /**
     * 动态规划：本题与上一题的求最长递增子序列相比，不同之处在于要求子序列必须连续，因此如果nums[i-1] < nums[i]，那么以i结尾的连续递增子序列长度一定等于以i-1结尾的连续递增子序列长度+1，故dp[i] = dp[i-1]+1
     * 确定dp数组及其下标含义：dp[i]表示以i结尾的连续递增子序列长度
     * 确定dp数组递推公式：由于是要求递增子序列，且连续，因此只有满足nums[i-1]<nums[i]时才记录其长度，即dp[i]=dp[i-1]+1
     * 确定dp数组初始化：每个数的最小递增子序列长度为，即其本身，故全部初始化为1
     *
     * @param nums 待查找的数组
     * @return 最长连续递增子序列长度
     */
    public int findLengthOfLCIS(int[] nums) {
        //边界情况
        if (nums.length == 1)
            return 1;
        //初始化dp数组
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        //记录最长连续递增子序列的长度
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            //满足递增的情况
            if (nums[i - 1] < nums[i])
                dp[i] = dp[i - 1] + 1;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 贪心算法：对于nums[i] > nums[i - 1]的情况，count就++，否则count为1，记录count的最大值
     * 时间复杂度：O(n)，空间复杂度：O(1)
     *
     * @param nums 待查找的数组
     * @return 最长连续递增子序列长度
     */
    public int findLengthOfLCIS2(int[] nums) {
        //count用于记录每一个连续递增子序列的长度，result用于存储最大的长度
        int count = 1;
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            //当前序列为递增，长度+1
            if (nums[i - 1] < nums[i])
                count++;
                //从头计算
            else
                count = 1;
            //记录count的最大值
            result = Math.max(result, count);
        }
        return result;
    }
}
