package com.java.Basic_algorithms.Array;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: Array_7
 * Package: com.java.Basic_algorithms.Array
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/2/29 13:47
 * @Version 1.0
 */
public class Array_7 {
    @Test
    public void test() {
        int[] ints = Solution7.plusOne(new int[]{9,9});
        System.out.println(Arrays.toString(ints));
    }
}

class Solution7 {
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            //如果数组当前元素不等于9，则加一直接返回
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        //只有当数组中元素全为9，才会执行到此处
        //数组中元素全为9，则把数组长度加一，然后把首位置为1即可
        digits=new int[digits.length+1];
        digits[0]=1;
        return digits;
    }
}