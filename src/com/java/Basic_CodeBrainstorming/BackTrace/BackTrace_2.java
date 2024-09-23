package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: BackTrace_2
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 * @Author chen_sir
 * @Create 2024/4/7 10:58
 * @Version 1.0
 */
public class BackTrace_2 {
    @Test
    public void test() {
        new Solution2().combinationSum3(3, 9);
    }
}

class Solution2 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    List<Integer> path = new ArrayList<>();// 用来存放符合条件结果

    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationTrace(k, n, 1, 0);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void combinationTrace(int k, int n, int startIndex, int curSum) {
        if (path.size() == k && curSum == n) {//满足条件，存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < n - curSum + 1; i++) {// n - curSum + 1也为剪枝操作
            if (curSum > n || path.size() > k - 1 || i > 9)// 剪枝操作，满足剪枝的条件：1、当前路径和大于n； 2、当前路径的数字个数大于k； 3、数字大于9
                continue;
            path.add(i);//处理节点
            curSum += i;
            combinationTrace(k, n, i + 1, curSum);//递归
            // 回溯，撤销处理过的节点
            path.remove(path.size() - 1);
            curSum -= i;
        }
    }
}
