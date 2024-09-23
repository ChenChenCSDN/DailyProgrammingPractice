package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: BackTrace_4
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * @Author chen_sir
 * @Create 2024/4/9 10:01
 * @Version 1.0
 */
public class BackTrace_4 {
    @Test
    public void test() {
        int[] candiates = {2, 3, 5};
        new Solution4().combinationSum(candiates, 8);
    }
}

class Solution4 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先进行排序，便于剪枝
        combinationTrace(candidates, target, 0, 0);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    //思路：同第一题的回溯思路，记录当前路径的数据，当满足sum==target时退出，重点在于该题目可以重复取当前元素，因此startIndex=i，而不是i+1
    /*
        什么时候要用startIndex记录移动位置？：当使用一个集合求组合的话，则需要startIndex
                                        当使用多个集合求组合的话，那么就不用startIndex
    */

    //时间复杂度: O(n * 2^n)
    //空间复杂度: O(target)
    public void combinationTrace(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {//当前路径和等于target，存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        // 如果 sum + candidates[i] > target 就终止遍历
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {//startIndex用于记录已经处理元素的位置，防止重复计算
            path.add(candidates[i]);
            sum += candidates[i];
            combinationTrace(candidates, target, sum, i);// 关键点:不用i+1了，表示可以重复读取当前的数
            sum -= candidates[i];// 回溯
            path.remove(path.size() - 1);// 回溯
        }
    }
}
