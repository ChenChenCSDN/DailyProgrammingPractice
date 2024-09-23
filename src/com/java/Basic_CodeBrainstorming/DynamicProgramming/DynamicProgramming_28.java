package com.java.Basic_CodeBrainstorming.DynamicProgramming;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: DynamicProgramming_28
 * Package: com.java.Basic_CodeBrainstorming.DynamicProgramming
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/8/19 14:57
 * @Version 1.0
 */
public class DynamicProgramming_28 {
    public static void main(String[] args) {
        new Solution28().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 2, 3});
//        new Solution28().lengthOfLIS(new int[]{7, 7, 7, 7, 7});
    }
}

class Solution28 {
    /**
     * 动态规划：
     * 确定dp数组及其下标的含义：dp[i]表示i之前包括下标i结尾的递增子序列的长度，比如dp[3]就是以下标3结尾的递增子序列的长度（0，1，3）
     * 确定dp数组递推公式：由于要求最长递增子序列的长度，因此需要对子序列中的每个元素进行比较，要比较子序列是否为递增序列只需从头遍历一遍，如果所有元素均小于当前元素，说明是递增的，将该元素放入即可。
     * 因此判断条件是if(nums[i] > nums[j]) dp[i] = max{dp[i],dp[j]+1} ，其本质就是以nums【i】为结尾的最长递增子序列的长度可以由 nums【0】为结尾的最长递增子序列长度、nums[1为结尾的最长长度、……nums【i-1】为结尾的最长长度比较得到，在获取到nums【i】的每个子串的时候,
     * 也就是nums【0】,nums【1】....nums【i-1】, 都尝试把i位置的数加入到这个子串里看能否添加成功.这就引入了为什么需要两层循环: 外层循环表示以 i 结尾的子串, 内层循环表示从0到i-1结尾的子串,下标用 j 来表示, 对每个子串, 都用子串的最后一个数和 i 进行 比较,
     * 如果大于i,则能添加到这个子串里. 为什么是最后一个数, 因为对于任意一个递增的子串, 最后一个数都是该子串的最大值. 最后取所有子串尝试加入 i 所得到的串的最大值
     * 时间复杂度：O(n^2)，空间复杂度：O(n)
     *
     * @param nums 待查找的整数数组
     * @return 最长递增子序列的长度
     */
    public int lengthOfLIS(int[] nums) {
        //边界条件
        if (nums.length <= 1) return nums.length;

        //初始化dp数组：每个以当前数组结尾的递增子序列最小为1，即其本身
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //存放最大子序列的长度
        int res = 1;

        //i控制从0遍历到nums.len-1，以此来判断数组内所有数字结尾的子序列长度
        for (int i = 1; i < dp.length; i++) {
            //j控制查找从0到i（i之前）的所有子序列的情况
            for (int j = 0; j < i; j++) {
                //nums[i] > nums[j]表示当前元素大于以nums[j]结尾的递增子序列的最后一个元素，因此可将其加入到递增子序列中，子序列长度+1
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //记录最大子序列的长度
            res = Math.max(res, dp[i]);
        }

        //打印dp数组，验证是否满足预期
        System.out.println(Arrays.toString(dp));

        //不返回dp[nums.length-1]是因为不一定最长递增子序列的长度就是以最后一个元素结尾的子序列长度，有可能在中间。
        return res;
    }

}
