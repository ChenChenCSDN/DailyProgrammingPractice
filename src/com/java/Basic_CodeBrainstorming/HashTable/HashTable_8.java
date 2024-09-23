package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: HashTable_8
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/14 18:54
 * @Version 1.0
 */
public class HashTable_8 {
    @Test
    public void test() {
        int[] nums = {-1000000000, -1000000000, 1000000000, -1000000000, -1000000000};

        int[] nums1 = {2, 2, 2, 2, 2};
        List<List<Integer>> lists = fourSum(nums, 294967296);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 找出a + b + c +d = 0
        // a = nums[i], b = nums[left], c = nums[right],d = nums[j]
        List<List<Integer>> result = new ArrayList<>();//存放结果数组
        Arrays.sort(nums);//原数组升序排序
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target && nums[i] > 0) // nums[i] > target 直接返回, 剪枝操作
                return result;
            if (i > 0 && nums[i] == nums[i - 1])    //a去重
                continue;
            int j = nums.length - 1;
            while ((i + 3) <= j) {  //移动d，
                int left = i + 1;
                int right = j - 1;
                while (left < right) {
                    long sum = (long) nums[i] + (long) nums[left] + (long) nums[right] + (long) nums[j];
                    if (sum > target)//排序后之和大于target，则右边界左移，使其和减小
                        right--;
                    else if (sum < target)//排序后之和小于target，则左边界右移，使其和变大
                        left++;
                    else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right], nums[j]));
                        //对b和c去重
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                }
                //对d去重，不能用left<right的条件，因为此时left一定大于right
                //while (left < right && nums[j] == nums[j - 1]) j--;
                while ((i + 3) <= j && nums[j] == nums[j - 1]) j--;
                j--;
            }
        }
        return result;
    }
}
