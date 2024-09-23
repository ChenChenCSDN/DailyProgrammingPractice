package com.java.Basic_CodeBrainstorming.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: HashTable_7
 * Package: com.java.Basic_CodeBrainstorming.HashTable
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/14 15:09
 * @Version 1.0
 */
public class HashTable_7 {
    @Test
    public void test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums1 = {1, -1, -1, 0};
        List<List<Integer>> lists = threeSum(nums1);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 找出a + b + c = 0
        // a = nums[i], b = nums[left], c = nums[right]
        List<List<Integer>> result = new ArrayList<>();//存放结果数组
        Arrays.sort(nums);//原数组升序排序
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)//排序后的第一个元素大于0，那么无论如何组合都不可能凑成三元组，直接返回结果
                return result;
            if (i > 0 && nums[i] == nums[i - 1])//去重a
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {//排序后之和大于0，则右边界左移，使其和减小
                    right--;
                } else if (sum < 0) {//排序后之和小于0，则左边界右移，使其和变大
                    left++;
                } else {//a + b + c = 0
                    List<Integer> temp = new ArrayList<>();
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //对b和c去重
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    //此时sum=0，左边界往右移，右边界往左移，查找其他sum=0的情况
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
