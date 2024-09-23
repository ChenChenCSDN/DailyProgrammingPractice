package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import com.sun.org.apache.xml.internal.security.Init;
import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_3
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 *给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组
 * 是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * @Author chen_sir
 * @Create 2024/4/23 10:06
 * @Version 1.0
 */
public class GreedyAlgorithm_3 {
    @Test
    public void test() {
        int[] nums = {-1,-1,-1};
        System.out.println(new Solution3().maxSubArray2(nums));
    }
}

class Solution3 {
    //法一：暴力求解法，使用两层for循环以此求出每个序列之和，记录其最大值
    //时间复杂度为：O(n^2)
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int count = 0;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            for (int j = i; j < nums.length; j++) {
                count += nums[j];
                result = count > result ? count : result;
            }
        }
        return result;
    }

    //法二：贪心算法：贪心算法贪的地方在于当前和为负数时，一定会拉低整体和的大小，要立刻放弃，从下一个元素开始计算和
    /*
    * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
      全局最优：选取最大“连续和”
      局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。
    * */
    //
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int count = 0;
        int result = Integer.MIN_VALUE;// 连续序列和，初始为最小值
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            result = Math.max(count, result);// 记录序列和的最大值
            if (count < 0)// 当前序列和为负数，舍弃
                count = 0;
        }
        return result;
    }
}
