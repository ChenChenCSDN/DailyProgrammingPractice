package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BcakTrace_8
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/12 15:02
 * @Version 1.0
 */
public class BackTrace_8 {
    @Test
    public void test() {
        new Solution8().subsets(new int[]{1,2});
    }
}

class Solution8 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    List<Integer> path = new ArrayList<>();// 用来存放符合条件结果

    public List<List<Integer>> subsets(int[] nums) {
        subTrace(nums, 0);
        result.add(new ArrayList<>());
        for (List<Integer> list : result) {
            System.out.println(list);
        }

        return result;
    }


    public void subTrace(int[] nums, int startIndex) {
        if (startIndex == nums.length) {// 因为会遍历到尾部，因此终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            result.add(new ArrayList<>(path));// 收集每一个结点记录
            subTrace(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
