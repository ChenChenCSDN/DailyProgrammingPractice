package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.nio.file.Path;
import java.util.*;

/**
 * ClassName: BackTrace_10
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/13 14:18
 * @Version 1.0
 */
public class BackTrace_10 {
    @Test
    public void test() {
        new Solution10().findSubsequences(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1});
    }
}

class Solution10 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        findSubTrac(nums, 0);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void findSubTrac(int[] nums, int startIndex) {
        if (path.size() > 1)
            result.add(new ArrayList<>(path));
        // 注意这里不要加return，要取树上的节点，还要继续往下搜索
        if (startIndex == nums.length)
            return;
        Set<Integer> set = new HashSet<>();// 每层都创建一个set，记录当前树层元素是否重复使用
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && path.get(path.size() - 1) > nums[i] || set.contains(nums[i]))// 剪枝：只有一个元素或不满足递增或取到重复元素则跳过当前遍历
                continue;
            path.add(nums[i]);
            set.add(nums[i]);// 记录这个元素在本层用过了，本层后面不能再用了
            //如1，2，3，1，1，4；第一次遍历到1时set就会记录，当再遍历到后面的1时，要直接跳过，因为第一次的1的遍历情况会包含后面所有的1的情况，所以不存在漏选的情况
            findSubTrac(nums, i + 1);
            path.remove(path.size() - 1);// 回溯
        }
    }
}

