package com.java.Basic_CodeBrainstorming.BackTrace;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: BackTrace_12
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * @Author chen_sir
 * @Create 2024/4/15 23:08
 * @Version 1.0
 */
public class BackTrace_12 {
    @Test
    public void test() {
        new Solution12().permuteUnique(new int[]{1, 1, 2});
    }
}

class Solution12 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];// 标记数组，用于记录同一树枝结点使用情况
        permuteTrace(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void permuteTrace(int[] nums) {
        if (path.size() == nums.length) {// 收割叶子结点，此时说明找到了一组
            result.add(new ArrayList<>(path));
            return;
        }
        Set<Integer> set = new HashSet<>();// 用于记录树层元素使用情况
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || set.contains(nums[i]))// 树枝元素已使用或树层元素已使用，则跳过
                continue;
            set.add(nums[i]);// 树层元素使用情况记录
            used[i] = true;// 树枝元素使用情况记录
            path.add(nums[i]);
            permuteTrace(nums);
            path.remove(path.size() - 1);// 回溯
            used[i] = false;// 回溯，更新树枝元素使用情况
        }
    }
}
