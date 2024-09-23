package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Array_1
 * Package: com.java.Basic_algorithms
 * Description:
 *
 * @Author chen_sir
 * @Create 2023/11/12 21:30
 * @Version 1.0
 */
public class Array_1 {
    @Test
    public void test() {
//        int[] nums = new int[]{1,1,2};
        int[] nums = new int[]{1,1,1,2,2,3,4,4};
        Solution.removeDuplicates(nums);
    }
}

class Solution {
    //个人题解：
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {//数组为空，返回0
            return 0;
        }
        int mount = 0;//数组中重复元素的数目
        for (int i = 0; i < nums.length - 1; i++) { //循环结束条件为nums.length - 1
            if (nums[i] == nums[i + 1]) {   //元素重复，记录重复元素的个数
                mount++;
            } else {  //元素不重复
                nums[i - mount + 1] = nums[i + 1];//将未重复元素移至数组前面的位置
            }
        }
    //打印当前数组的状态
        System.out.println(Arrays.toString(nums));
        return mount;
    }

    //官方题解：
    public static int removeDuplicates2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int index = 0;
        int result = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[index]){
                nums[result] = nums[i];
                result++;
                index = i;
            }
        }
        System.out.println(Arrays.toString(nums));
        return result;
    }
}