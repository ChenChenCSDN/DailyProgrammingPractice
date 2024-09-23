package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

/**
 * ClassName: String_1
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/15 11:16
 * @Version 1.0
 */
public class String_1 {
    //双指针
    public void reverseString(char[] s) {
        //双指针：左指针指向头，右指针指向尾
        int i = 0, j = s.length - 1;
        char temp;
        while (i < j) {//双指针往中间移动，两两交换
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            //指针移动
            i++;
            j--;
        }
    }
}
