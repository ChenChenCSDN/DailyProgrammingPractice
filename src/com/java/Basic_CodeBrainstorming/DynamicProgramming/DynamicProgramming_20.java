package com.java.Basic_CodeBrainstorming.DynamicProgramming;

/**
 * ClassName: DynamicProgramming_20
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/16 15:28
 * @Version 1.0
 */
public class DynamicProgramming_20 {
    public static void main(String[] args) {
        new Solution20().rob(new int[]{1, 2, 3, 1});
    }
}

class Solution20 {
    /**
     * 动态规划：本题与之前的打家劫舍问题的区别点在于本题的首尾房屋是相邻的，也就是为环形数组，因此如果偷首，那就不能偷尾，如果偷尾，那就不能偷首。
     * 因此可以将题目分成两种情况，一是偷首，不偷尾，二是偷尾不偷首，比较两种情况下的最大值即可。其余解题思路同上一题。
     * 确定dp数组及其下标含义：dp[i]表示考虑下标start到end（包括）范围内个房屋，能够偷取的金额最大值。
     * 确定dp数组递推公式：dp[i] = max{dp[i-2]+nums[i] , dp[i-1]}
     * 确定dp数组初始化：dp[start] = nums[start]，因为两种情况下dp数组的首元素不同，故赋值方式不同
     * 确定dp数组遍历方式：从前往后遍历
     *
     * @param nums 待查找的房屋数组
     * @return 能够偷取的金额最大值
     */
    public int rob(int[] nums) {
        //边界情况：只有一间或两间房屋
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        //RobMoney(nums, 0, nums.length - 2)表示考虑从0~nums.length - 2(包括）范围内的房屋能够偷取的最大金额数，即偷首不偷尾。
        //RobMoney(nums, 0, nums.length - 2)表示考虑从1~nums.length - 1(包括）范围内的房屋能够偷取的最大金额数，即偷尾不偷首。
        return Math.max(RobMoney(nums, 0, nums.length - 2), RobMoney(nums, 1, nums.length - 1));
    }

    public int RobMoney(int[] nums, int start, int end) {
        //初始化dp数组
        int[] dp = new int[nums.length];
        //根据选择开始偷的第一间房屋的不同进行初始化
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        //遍历dp数组
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        //dp[end]表示考虑end前（包括）所有的房屋，能够偷取的最大金额数
        return dp[end];
    }
}
