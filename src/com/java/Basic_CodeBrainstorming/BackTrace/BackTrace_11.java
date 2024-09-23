package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.*;
import java.util.function.IntPredicate;

/**
 * ClassName: BackTrace_11
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/14 22:33
 * @Version 1.0
 */
public class BackTrace_11 {
    @Test
    public void test() {
        new Solution11().permute(new int[]{1, 1, 2});
    }
}

class Solution11 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    Set<Integer> set = new HashSet<>();

    /*
    * 思路：每层都是从0开始搜索而不是startIndex
           需要记录path里都放了哪些元素了：使用标志数字used或set记录，也可以通过判断path中是否存在数字，排除已经选择的数字
    * */

    //法一（仅适用于不含重复元素的数组）：使用set充当标志数组，来记录已使用过的数字
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        permuteTrace2(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void permuteTrace(int[] nums) {
        // 此时说明找到了一组
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))// set中已经记录过的元素，直接跳过
                continue;
            path.add(nums[i]);
            set.add(nums[i]);// set中当前未记录过该元素，记录
            permuteTrace(nums);
            path.remove(path.size() - 1);
            set.remove(nums[i]);// 使用完毕该元素，还原标志位
        }
    }


    //法二：使用数组used充当标志数组，来记录已使用过的数字
    boolean[] used;// 成员变量，需赋值

    public void permuteTrace2(int[] nums) {
        // 此时说明找到了一组
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true)// used中已经记录过的元素，直接跳过
                continue;
            path.add(nums[i]);
            used[i] = true;// used中当前未记录过该元素，记录
            permuteTrace2(nums);
            path.remove(path.size() - 1);// 回溯
            used[i] = false;// 使用完毕该元素，还原标志位
        }
    }

    //法三（仅适用于不含重复元素的数组）：通过判断path中是否存在数字，排除已经选择的数字
    public void permuteTrace3(int[] nums) {
        // 此时说明找到了一组
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i]))// 如果path中已有，则跳过
                continue;
            path.add(nums[i]);
            permuteTrace(nums);
            path.remove(path.size() - 1);// 回溯
        }
    }


}
