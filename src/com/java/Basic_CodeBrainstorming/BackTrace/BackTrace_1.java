package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: BackTrack_1
 * Package: com.java.Basic_CodeBrainstorming.BackTrack
 * Description:
 * 77. 组合：https://leetcode.cn/problems/combinations/description/
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 *
 * @Author chen_sir
 * @Create 2024/4/6 9:52
 * @Version 1.0
 */
public class BackTrace_1 {
    @Test
    public void test() {
        new Solution1().combine(4, 3);
    }
}

class Solution1 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    List<Integer> path = new ArrayList<>();// 用来存放符合条件结果

    public List<List<Integer>> combine(int n, int k) {
        combineBackTrace2(n, k, 1);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    // 思路：回溯，确定边界条件：当前路径结果存放的数满足k，存入结果集中；同时为防止出现重复的组合，需记录开始位置（startIndex）
    // 时间复杂度: O(n * 2^n)
    // 空间复杂度: O(n)
    public void combineBackTrace(int n, int k, int startIndex) {
        if (path.size() == k) {// 当前路径满足条件，存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);// 处理节点
            combineBackTrace(n, k, i + 1);// 递归
            path.remove(path.size() - 1);// 回溯，撤销处理过的节点
        }
    }

    // 思路：回溯，确定边界条件：当前路径结果存放的数满足k，存入结果集中；同时为防止出现重复的组合，需记录开始位置（startIndex）
    public void combineBackTrace2(int n, int k, int startIndex) {
        if (path.size() == k) {// 当前路径满足条件，存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; k - path.size() <= n - i + 1; i++) {//剪枝操作
            path.add(i);// 处理节点
            combineBackTrace2(n, k, i + 1);// 递归
            path.remove(path.size() - 1);// 回溯，撤销处理过的节点
        }
    }
}
