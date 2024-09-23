package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: BackTrace_9
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
 * 子集
 * （幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * @Author chen_sir
 * @Create 2024/4/13 13:56
 * @Version 1.0
 */
public class BackTrace_9 {
    @Test
    public void test() {
        new Solution9().subsetsWithDup(new int[]{4, 4, 4, 1, 4});
    }
}

class Solution9 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    List<Integer> path = new ArrayList<>();// 用来存放符合条件结果

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result.add(new ArrayList<>());
        Arrays.sort(nums);// 数组元素排序预处理，便于“树层去重”
        subsetTrace(nums, 0);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void subsetTrace(int[] nums, int startIndex) {
        if (startIndex == nums.length)// 遍历到数组末尾，所有结点都处理过，退出递归
            return;
        for (int i = startIndex; i < nums.length; i++) {
            // 跳过当前树层使用过的、相同的元素
            if (i > startIndex && nums[i] == nums[i - 1])
                continue;
            path.add(nums[i]);
            result.add(new ArrayList<>(path));// 将每个结点路径存入结果集
            subsetTrace(nums, i + 1);
            path.remove(path.size() - 1);// 回溯
        }
    }
}
