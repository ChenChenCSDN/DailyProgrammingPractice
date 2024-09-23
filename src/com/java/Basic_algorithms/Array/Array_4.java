package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: Array_4
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2023/11/15 16:11
 * @Version 1.0
 */
public class Array_4 {
    @Test
    public void test(){
        int[]nums = new int[]{1,2,3,4,5,6,6};
        System.out.println(findRepeatElements.containsDuplicate(nums));
    }
}

class findRepeatElements {
    //方法1：将数组通过sort()函数进行升序排序，然后遍历排序后的数组，判断是否存在相同相邻元素，则返回true
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);//数组升序排序
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i]==nums[i+1]){ //排序后的数组相邻元素相同，则返回true
                return true;
            }
        }
        return false;//未存在相同的相邻元素，返回false
    }

    //方法2：利用set集合中元素不重复的特点，将数组元素放到set集合中，若存在重复元素，则插入set时会把之前的值给覆盖，并且返回false
    public static boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>(); //Set<Integer>集合用于存放nums数组中的元素
        for(int i:nums){//遍历nums数组中的元素，将其放入到set集合中
            if (!set.add(i)) {//set集合中存在重复元素，则add()返回false
                return true;
            }
        }
        //不存在重复元素
        return false;
    }

}