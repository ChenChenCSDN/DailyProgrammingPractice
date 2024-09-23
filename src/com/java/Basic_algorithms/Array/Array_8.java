package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Array_8
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/2/29 14:38
 * @Version 1.0
 */
public class Array_8 {
    @Test
    public void test() {
        Solution8.moveZeroes(new int[]{0, 1, 0, 3, 12});
    }
}

class Solution8 {
    public static void moveZeroes(int[] nums) {
        int count = 0;//记录数组中0的个数
        for (int i = 0; i < nums.length; i++) {
            //当前数组元素为0，记录个数，其余元素位置不动
            if (nums[i] == 0) {
                count++;
            } else if (count != 0) { //数组中含有0元素，移动当前元素至前面
                nums[i - count] = nums[i];
                nums[i] = 0;//移动后当前位置归零
            }
        }
    }
}
