package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_19
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/15 22:59
 * @Version 1.0
 */
public class DynamicProgramming_19 {
    public static void main(String[] args) {
        new Solution19().rob(new int[]{2, 7, 9, 3, 1});
    }
}

class Solution19 {
    /**
     * 动态规划：当前房间偷还是不偷取决于前一间和前两间房屋中偷的是那一间，故当前状态与之前的状态有关，可以使用动态规划进行求解。
     * 确定dp数组及其下标含义：dp[i]表示考虑下标i（包括i）以内的房屋，可以偷取的最大金额数目
     * 确定dp递推公式：当前房间偷还是不偷取决于前一间和前两间房屋中偷的是那一间，dp[i-1]是前一间房屋的状态，dp[i-2]是前两间的状态，若偷第i-2间，则dp[i] = dp[i-2] + nums[i]，若不偷第i间，则dp[i] = dp[i-1]，
     * 由于是要求最大金额数，故取二者之间的最小值：dp[i] = min{dp[i-2] + nums[i],dp[i-1]}
     * 确定dp数组初始化：根据dp数组的含义，可知dp[0] = nums[0]，dp[1] = max(nums[0],nums[1])
     * 确定dp数组遍历顺序：易知dp[i]由前面的状态决定，故从前往后遍历
     *
     * @param nums 待遍历的房屋数组
     * @return 最大金额数
     */
    public int rob(int[] nums) {
        //边界情况：只有一间房屋，直接返回当前房屋的金额数
        if (nums.length == 1)
            return nums[0];

        //初始化dp数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //从前向后遍历dp数组
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        //dp[nums.length-1]表示前nums.length-1(包括nums.length-1)中可以偷取到的最大金额数
        return dp[nums.length - 1];
    }
}