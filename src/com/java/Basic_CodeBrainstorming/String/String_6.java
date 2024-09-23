package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

/**
 * ClassName: String_6
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/18 14:13
 * @Version 1.0
 */
public class String_6 {
    @Test
    public void test() {
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length())//模式串长度大于源字符串，一定不匹配，返回-1
            return -1;
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            int k = i;
            Boolean flag = true;
            while (j < needle.length() && k < haystack.length()) {//模式串与源字符串一一匹配
                if (needle.charAt(j) == haystack.charAt(k)) {
                    j++;
                    k++;
                } else {//存在不匹配的字符，标识为置为false
                    j = 0;
                    flag = false;
                    break;
                }
            }
            //j==needle.length()用于判断aabb和bba的情况
            if (flag == true && j == needle.length())//标识位为true说明，一一匹配
                return k - needle.length();
        }
        return -1;
    }
}
