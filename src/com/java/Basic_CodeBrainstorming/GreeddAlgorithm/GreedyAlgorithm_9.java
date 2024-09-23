package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: GreedyAlgorithm_9
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 * <p>
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 * @Author chen_sir
 * @Create 2024/5/12 10:35
 * @Version 1.0
 */
public class GreedyAlgorithm_9 {
    @Test
    public void test() {
        int[] ratings = {1, 3, 4, 5, 2};
        System.out.println(new Solution9().candy(ratings));
    }
}

class Solution9 {
    //  解题思路：对于要同时考虑左右两边的情况，解题思路是先统一比较一边，再比较另一边，同时比较左右的话会出现顾此失彼的情况
    //  贪心策略：
    //  一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
    //  一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
    // 局部最优：当前孩子满足同时大于左右，整体最优：整体满足，且所需糖果个数最小
    // 时间复杂度：O(n)
    public int candy(int[] ratings) {
        int count = 0;
        int[] candyVec = new int[ratings.length];
        Arrays.fill(candyVec, 1);
        // 从左往后，比较右孩子评分大于左孩子的情况
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) candyVec[i] = candyVec[i - 1] + 1;
        }

        // 从右往左，比较左孩子评分大于右孩子的情况
        for (int i = ratings.length - 2; i >= 0; i--) {
            //  candyVec[i]是右孩子大于左孩子的情况下取得的值
            //  candyVec[i+1]+1是左孩子大于右孩子的情况下取得的值
            //  Math.max(candyVec[i],candyVec[i+1]+1)同时比较左孩子和右孩子的糖果个数，确保同时大于两边的情况
            if (ratings[i] > ratings[i + 1]) candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
        }

        //  所需糖果个数求和
        for (int i : candyVec) {
            count += i;
        }
        return count;
    }
}
