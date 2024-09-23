package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_2
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * <p>
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 * <p>
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * <p>
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 *
 * @Author chen_sir
 * @Create 2024/4/22 11:16
 * @Version 1.0
 */
public class GreedyAlgorithm_2 {
    @Test
    public void test() {
        int[] nums = {1, 10, 5, 5, 7, 4, 3, 2, 2, 5};
        System.out.println(new Solution2().wiggleMaxLength(nums));
    }
}

class Solution2 {
    //思路：要找摆动序列最大，即波峰越多越好，因此可以删除不为波峰的节点使局部成为波峰。
    //整体最优：整个序列有最多的局部峰值，从而达到最长摆动序列。
    //时间复杂度：O(n)
    //空间复杂度：O(1)
    /*要考虑的三种情况：
        情况一：上下坡中有平坡
        情况二：数组首尾两端
        情况三：单调坡中有平坡*/
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int count = 1;//当前摆动序列长度
        int preDiff = 0;//上一个差值
        int curDiff = 0;//当前差值
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if (curDiff > 0 && preDiff <= 0 || curDiff < 0 && preDiff >= 0) {
                preDiff = curDiff;// 暂存之前的波峰差值情况
                count++;
            }
        }
        return count;
    }
}
