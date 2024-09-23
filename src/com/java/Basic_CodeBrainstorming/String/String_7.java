package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * ClassName: String_7
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/18 15:02
 * @Version 1.0
 */
public class String_7 {
    @Test
    public void test() {
        String s = "aaaabaaaab";
        System.out.println(repeatedSubstringPattern(s));
    }

    //思路：使用s.substring(0, ++i)依次取出字串，进行匹配，若不满足则退出
    public boolean repeatedSubstringPattern(String s) {
        Boolean flag = true;
        int j = 0, i = 0, m = 0, index = 0;
        while (i < s.length()) {
            String subStr = s.substring(0, ++i);
            if (subStr.length() == s.length())//字串等于源字符串，说明不能由最小字串构成
                return false;
            while (m < s.length()) {//当前循环遍历当前字串
                j = 0;
                while (j < subStr.length() && m < s.length()) {//字串与源字符串一一匹配
                    if (s.charAt(m) == subStr.charAt(j)) {
                        m++;
                        j++;
                    } else {
                        j = 0;
                        break;
                    }
                }
                if (j != subStr.length())//当前字串未遍历完毕，说明一定不能由字串构成
                    break;
            }
            if (m == s.length() && j == subStr.length())//源字符串和字串都遍历到尾部，说明可由字串构成
                return true;
            m = 0;//当前字串轮次匹配结束，比较起点归零
        }
        return false;
    }
}
