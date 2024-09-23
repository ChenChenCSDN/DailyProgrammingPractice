package com.java.Basic_CodeBrainstorming.BackTrace;

import org.hamcrest.core.SubstringMatcher;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: BackTrace_5
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 *
 * @Author chen_sir
 * @Create 2024/4/9 10:48
 * @Version 1.0
 */
public class BackTrace_5 {
    @Test
    public void test() {
        int[] candidates = {1,1};
        new Solution5().combinationSum2(candidates, 1);
    }
}

class Solution5 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        combinationTrace2(new ArrayList<>(), candidates, target, 0, 0);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    //思路：本题区别于题1的难点在于集合（数组candidates）有重复元素，但还不能有重复的组合
    /*
    * 所谓去重，其实就是使用过的元素不能重复选取。
    * 都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，一个维度是同一树枝上使用过，一个维度是同一树层上使用过。没有理解这两个层面上的“使用过” 是造成大家没有彻底理解去重的根本原因。
      那么问题来了，我们是要同一树层上使用过，还是同一树枝上使用过呢？
      回看一下题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。
      所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重。
    * */
    public void combinationTrace2(List<Integer> path, int[] candidates, int target, int startIndex, int sum) {
        if (sum == target) {//当前路径结果之和满足sum==target，存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {//sum + candidates[i] <= target进行剪枝操作
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
            if(i > startIndex && candidates[i] == candidates[i - 1])
                continue;
            path.add(candidates[i]);
            sum += candidates[i];
            // i+1 代表当前组内元素只选取一次
            combinationTrace2(path, candidates, target, i + 1, sum);
            path.remove(path.size() - 1);//回溯
            sum -= candidates[i];//回溯
        }
    }
}
