package com.java.Basic_CodeBrainstorming.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Array_2
 * Package: com.java.Basic_CodeBrainstorming.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/3 10:17
 * @Version 1.0
 */
public class Array_2 {
    @Test
    public void test() {
        System.out.println(removeElement3(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    //法一：遍历数组，记录待移除整数的个数，根据待移除整数的个数来确定非待移除元素的下标
    public static int removeElement(int[] nums, int val) {
        int count = 0;//记录待移除整数的个数
        for (int i = 0; i < nums.length; i++) { //遍历数组，进行原地移除所有值为val的元素
            if (nums[i] == val) {//当前元素为待移除元素，count个数+1
                count++;
            } else {//当前元素非待移除元素，若count！=0，说明需移动此元素的位置
                nums[i - count] = nums[i];
            }
        }
        return nums.length - count;//移除后新长度为原数组长度-待移除元素的个数
    }

    //法二：法一同样的思想，但不记录待移除整数的个数，而是利用循环的特性使其自增
    //如果当前元素 x 与移除元素 val 相同，那么跳过该元素。
    //如果当前元素 x 与移除元素 val 不同，那么我们将其放到下标 idx 的位置，并让 idx 自增右移。
    public static int removeElement2(int[] nums, int val) {
        int idx = 0;
        for (int num : nums) {
            if (num != val) {//当前元素非待移除整数，移动到相应的位置
                nums[idx++] = num;
            }
        }
        return idx;
    }

    public static int removeElement3(int[] nums, int val) {
        int j=nums.length-1;
        int temp=0;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                //将待移除的数从有效区换至无效区内
                temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                i--;//防止从无效区换过来的数也是要待移除的数，需将i-1，保证下次还会比对该元素
                j--;//无效区左移一位
            }
        }
        //运行至此i一定大于j，j为有效区有边界，i为无效区左边界，有效区元素个数即为j+1
        return j+1;
    }
}

