package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Array_9
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/2/29 15:17
 * @Version 1.0
 */
public class Array_9 {
    @Test
    public void test() {
        System.out.println(Arrays.toString(Solution9.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}

class Solution9 {
    /**
     * @param nums   目标数组
     * @param target 目标之和
     * @description:法一：暴力法，挨个遍历数组，两两相加，直至找到为止，记录下标
     * @return: int[] 由数组下标组成的数组
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {//外层遍历
            for (int j = i + 1; j < nums.length; j++) {//内层遍历
                if (nums[i] + nums[j] == target) {//找到组成目标值的两个数，记录其下标,构成数组
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();//map用于存储元素值和下标组成的键值对
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {  //可由之前含有的元素构成目标值
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);//不能与之前的元素构成目标值，存储到map中
        }
        return new int[]{0, 0};
    }
}