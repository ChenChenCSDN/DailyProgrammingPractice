package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName: String_5
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/17 20:53
 * @Version 1.0
 */
public class String_5 {
    //法一：使用字符串数组来根据下标对应关系实现右逆转
    public static String reverseRightStep(String s, int k) {
        char[] result = new char[s.length()];
        if (k % s.length() == 0)
            return String.valueOf(result);
        char temp;
        for (int i = 0; i < s.length(); i++) {//根据下标关系逆置
            result[(i + k) % s.length()] = s.charAt(i);
        }
        return String.valueOf(result);
    }
}
