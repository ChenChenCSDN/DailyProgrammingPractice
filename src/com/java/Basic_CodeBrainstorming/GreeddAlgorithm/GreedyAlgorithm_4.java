package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_4
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * @Author chen_sir
 * @Create 2024/4/24 9:44
 * @Version 1.0
 */
public class GreedyAlgorithm_4 {
    @Test
    public void test() {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(new Solution4().canJump(nums));
    }
}

class Solution4 {
    // 思路：记录当前剩余可跳跃步数，每次跳跃一步，当跳到的位置可跳跃步长大于剩余可跳跃步长时，更新剩余可跳跃步长，判断是否能跳到终点
    // 时间复杂度: O(n)
    // 空间复杂度: O(1)
    public boolean canJump(int[] nums) {
        if (nums.length == 1)
            return true;
        int curIndex = 0;// 当前位置
        int curStepLen = nums[curIndex];// 当前可跳跃的长度
        while (curStepLen > 0) {
            curStepLen--;// 跳跃一步
            curIndex++;// 当前位置前进一格
            if (curIndex == nums.length - 1)// 当前位置到达终点
                return true;
            if (nums[curIndex] > curStepLen)// 当前位置可跳跃步长大于剩余可跳跃步长，更新可跳跃步长
                curStepLen = nums[curIndex];
        }
        // 跳完未到达终点，说明不可到达
        return false;
    }
}
