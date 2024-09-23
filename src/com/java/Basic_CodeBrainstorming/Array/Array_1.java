package com.java.Basic_CodeBrainstorming.Array;

import org.junit.Test;

/**
 * ClassName: BinarySearch
 * Package: com.java.Basic_CodeBrainstorming.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/3 9:51
 * @Version 1.0
 */
public class Array_1 {
    @Test
    public void test() {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 92));
    }

    public static int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int mid = 0;
        while (i <= j) {
            mid = (i + j) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }
}

