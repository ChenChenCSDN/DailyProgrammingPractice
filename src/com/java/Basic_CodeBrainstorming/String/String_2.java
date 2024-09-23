package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: String_2
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/15 11:24
 * @Version 1.0
 */
public class String_2 {
    @Test
    public void test() {
        String s = "1234567890";
        String string = reverseStr(s, 15);
        System.out.println(string);
    }

    /*
        将字符数组转为字符串的方法：
            法一：char[] charArray = {'H', 'e', 'l', 'l', 'o'};
                 String str = new String(charArray);
            法二：
                 char[] charArray = {'H', 'e', 'l', 'l', 'o'};
                 String str = String.valueOf(charArray);
     */
    public static String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();//字符串转为字符数组，便于操作
        int i = 0, j = k - 1;
        while (i < charArray.length && j < charArray.length) {//计数至 2k 个字符，反转这 2k 字符中的前 k 个字符的情况
            reverse(charArray, i, j);
            i = i + 2 * k;
            j = j + 2 * k;
        }
        if (j >= charArray.length) {//字符串长度小于k及剩余字符小于2k但大于或等于k，反转前k个字符的情况
            reverse(charArray, i, charArray.length - 1);
        }
        return String.valueOf(charArray);
    }

    //反转字符串数组中从i~j的字符
    public static void reverse(char[] s, int i, int j) {
        char temp;
        while (i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
