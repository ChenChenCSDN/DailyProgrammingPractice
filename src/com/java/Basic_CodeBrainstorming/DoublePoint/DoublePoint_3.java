package com.java.Basic_CodeBrainstorming.DoublePoint;

import org.junit.Test;

/**
 * ClassName: DoublePoint_3
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/19 10:50
 * @Version 1.0
 */
public class DoublePoint_3 {
    @Test
    public void test() {
        String s = "a1b2c3";
        System.out.println(replaceNumber(s));
    }

    //思路：创建一个新的大小为原数组中数字替换为number之后的数组，例如 字符串 "a5b" 的长度为3，那么 将 数字字符变成字符串 "number" 之后的字符串为 "anumberb" 长度为 8。
    // 然后从后向前替换数字字符，也就是双指针法，过程如下：i指向新长度的末尾，j指向旧长度的末尾。
    public String replaceNumber(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {//遍历原字符串，统计其中数字的个数
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z')
                count++;
        }
        int newSize = (s.length() - count) + count * 6;//原数组中数字替换为number之后的新数组大小
        char[] result = new char[newSize];//新的字符数组，存放数字替换为number替换后的字符串
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') {//遍历到原字符串中的数字，将其用number替换到新的数组中去
                result[--newSize] = 'r';
                result[--newSize] = 'e';
                result[--newSize] = 'b';
                result[--newSize] = 'm';
                result[--newSize] = 'u';
                result[--newSize] = 'n';
            } else//遍历到非数字，直接放入到新数组中
                result[--newSize] = s.charAt(i);
        }
        return new String(result);
    }
}
