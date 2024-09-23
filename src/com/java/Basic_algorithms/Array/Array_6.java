package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Array_6
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/2/28 16:15
 * @Version 1.0
 */
public class Array_6 {
    @Test
    public void test() {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9,4,9,8,4};
        int[] intersect = Solution5.intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }
}

class Solution5 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        //将数组nums1和nums2升序排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        //list集合用于存储相同的数据
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;//i和j分别指向两个数组的起始位置
        while (i < nums1.length && j < nums2.length){    //i指向nums1数组，j指向nums2数组
            if (nums1[i] == nums2[j]) { //i和j所指向的元素相同，放入list中，再同时后移一步
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) { //i所指向元素大于j所指向元素，i不动，j后移一步
                j++;
            } else { //j所指向元素大于i所指向元素，j不动，i后移一步
                i++;
            }
        }
        int index = 0;
        int[] result = new int[list.size()];//存储结果的数组result
        for (Integer number : list) {//将list转换成数组result
            result[index++] = number;
        }
        return result;
    }
}

