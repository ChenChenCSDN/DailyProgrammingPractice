package com.java.Basic_CodeBrainstorming.GreeddAlgorithm;

import org.junit.Test;

/**
 * ClassName: GreedyAlgorithm_5
 * Package: com.java.Basic_CodeBrainstorming.GreeddAlgorithm
 * Description:
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * <p>
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <p>
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * @Author chen_sir
 * @Create 2024/4/24 10:30
 * @Version 1.0
 */
public class GreedyAlgorithm_5 {
    @Test
    public void test() {
        int[] nums = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(new Solution5().jump(nums));
    }
}

class Solution5 {
    //贪心的思路，局部最优：当前可移动距离尽可能多走，如果还没到终点，步数再加一。整体最优：一步尽可能多走，从而达到最少步数。
    public int jump(int[] nums) {
        if (nums.length == 1)
            return 0;
        int count = 0; //记录跳跃的次数
        int curDistance = 0;//当前的覆盖最大区域
        int maxDistance = 0;//最大的覆盖区域
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance >= nums.length - 1) {
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域,跳跃次数加一
            if (i == curDistance) {
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }
}
