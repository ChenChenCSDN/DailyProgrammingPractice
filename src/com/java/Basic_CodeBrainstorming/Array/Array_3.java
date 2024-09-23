package com.java.Basic_CodeBrainstorming.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Array_3
 * Package: com.java.Basic_CodeBrainstorming.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/3 18:27
 * @Version 1.0
 */
public class Array_3 {
    @Test
    public void test() {
        System.out.println(Arrays.toString(sortedSquares2(new int[]{-4, -1, 0, 3, 10})));
    }

    //法一：将数组内的所有元素开平方，然后再使用快排进行升序排序，优点是易想到，缺点是时间复杂度高：O(n*logn)
    public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {//原地将数组内的元素开平方
            nums[i] = (int) Math.pow(nums[i], 2);
        }
        //将平方后的数组升序排序，时间复杂度O(n*logn)
        Arrays.sort(nums);
        return nums;
    }

    //法二：双指针法，由于数组内元素已是递增，因此最大值元素一定是在两侧，不会出现在中间，故创建一个新的数组，使用双指针向数组中间逼近，比较双侧值的大小，将大的数放入新的数组的右侧，保证递增
    public static int[] sortedSquares2(int[] nums) {
        int[] result = new int[nums.length];//用于存储递增后的数组
        int index = nums.length - 1;//从右往数组内插入元素，以此来保证递增
        int i = 0, j = nums.length - 1;//双指针，指向数组两侧
        while (i <= j) {//当左指针大于右指针时，退出循环
            if (nums[i] * nums[i] > nums[j] * nums[j]) {//左指针平方大于右指针平方，将左指针元素从右插入到新数组中，左指针右移一位
                result[index--] = nums[i] * nums[i];
                i++;
            } else {//左指针平方小于等于右指针平方，将右指针元素从右插入到新数组中，右指针左移一位
                result[index--] = nums[j] * nums[j];
                j--;
            }
        }
        return result;//排序后的新数组
    }
}

