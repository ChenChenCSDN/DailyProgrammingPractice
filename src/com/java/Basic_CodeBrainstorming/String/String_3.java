package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: String_3
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/15 13:24
 * @Version 1.0
 */
public class String_3 {
    @Test
    public void test() {
        String s = "a1b1c1";
        System.out.println(replaceNumber2(s));
    }

    //创建一个新的空字符串，然后依次遍历字符串s，当字符为‘a’-‘z’时直接插入到后面，否则插入‘number’
    //时间复杂度为O(n)，空间复杂度为O(1)，因为StringBuffer是变量，可在原字符串上进行修改
    public static String replaceNumber(String s) {
        char[] charArray = s.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] < 'a' || charArray[i] > 'z') {//当前字符不为字母，插入‘number’
                result.append("number");
            } else//当前字符为字母，直接插入
                result.append(charArray[i]);
        }
        return result.toString();
    }

    //方法同上，不同之处在于先将每个字符插入到list中，然后依次插入到StringBuffer中
    public static String replaceNumber2(String s) {
        char[] charArray = s.toCharArray();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] < 'a' || charArray[i] > 'z') {
                list.add("number");
            } else
                list.add(String.valueOf(charArray[i]));
        }
        StringBuffer result = new StringBuffer();
        for (String string : list) {
            result.append(string);
        }
        return result.toString();
    }
}
