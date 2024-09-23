package com.java.Basic_algorithms.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: String_1
 * Package: com.java.Basic_algorithms.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/1 11:56
 * @Version 1.0
 */
public class String_1 {
    @Test
    public void test() {
        Solution1.reverseString(new char[]{'H','a','n','n','a','h'});
    }
}

class Solution1 {
    public static void reverseString(char[] s) {
        char temp;//存储待反转的字符
        int i = 0, j = s.length - 1;//i指向数组左边界，j指向右边界
        while (i < j) {//从左向右依次反转，直至左边界下标大于右边界下标
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
