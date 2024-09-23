package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 * ClassName: GreedyAlgorithm_7
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 *
 * @Author chen_sir
 * @Create 2024/5/9 11:12
 * @Version 1.0
 */
public class GreedyAlgorithm_7 {
    @Test
    public void test() {
        int[] nums = {3, -1, 0, 2};
        System.out.println(new Solution7().largestSumAfterKNegations(nums, 3));
    }
}

class Solution7 {
    // 思路：将数组排序，每次反转最小的元素（排序后的第一个元素），重复k次，最后求和即可
    // 局部最优：让绝对值大的负数变为正数，当前数值达到最大，让最小的正数变为负数，当前数值达到最小
    // 全局最优：整个数组和达到最大
    // 时间复杂度：O（nlogn）
    // 空间复杂度：O（1）
    public int largestSumAfterKNegations(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            Arrays.sort(nums);// 数组升序排序
            nums[0] = -nums[0];// 反转最小的元素
        }
        for (int num : nums) {// 求和
            count += num;
        }
        return count;
    }
}
