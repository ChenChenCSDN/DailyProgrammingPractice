package com.java.Basic_algorithms.Array;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * ClassName: Array_3
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2023/11/14 20:47
 * @Version 1.0
 */
public class Array_3 {
    @Test
    public void test() {
//        int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] nums = new int[]{8,7,6,5,4,3,2,1};
        ReverseArray reverseArray = new ReverseArray();
        reverseArray.rotate(nums, 1);
    }
}

class ReverseArray {
    //方法1：使用一个临时数组，将移位后的数组存储在临时数组中后，然后再赋回给原数组。通过（i+k）%length
    public static void rotate(int[] nums, int k) {
        int[]temp = new int[nums.length];//临时数组，存放反转后的数组
        for (int i = 0; i < nums.length; i++) {
            temp[(i+k)%nums.length]=nums[i];    //原数组元素轮转后赋值到临时数组中
        }
        System.out.println(Arrays.toString(temp));
        for (int i = 0; i < nums.length; i++) { //轮换后的数组重新赋值给原数组
            nums[i]=temp[i];
        }
        System.out.println(Arrays.toString(nums));
    }

    //方法2：多次反转
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        k %= length;    //k大于length的情况
        reverse(nums, 0, length - 1);//反转全部元素
        reverse(nums, 0, k - 1);//反转前k个元素
        reverse(nums, k, length - 1);//反转剩下的元素
    }

    public void reverse(int[] nums, int low, int high) {//反转数组
        int temp = 0;
        while (low < high) {    //low和high向两边逼近
            temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
}
