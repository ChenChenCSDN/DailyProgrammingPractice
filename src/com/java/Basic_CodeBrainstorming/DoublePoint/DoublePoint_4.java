package com.java.Basic_CodeBrainstorming.DoublePoint;

import org.junit.Test;

/**
 * ClassName: DoublePoint_4
 * Package: com.java.Basic_CodeBrainstorming.DoublePoint
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/19 18:52
 * @Version 1.0
 */
public class DoublePoint_4 {
    @Test
    public void test() {
        String s = "  hello world  ";
        System.out.println(reverseWords(s));
    }

    public String reverseWords(String s) {
        StringBuffer result = removeSpace(s);
        reverse(result, 0, result.length() - 1);
        reverseEachWords(result);
        return result.toString();
    }

    //删除前后及中间多余的空格
    public StringBuffer removeSpace(String s) {
        StringBuffer newS = new StringBuffer();
        int start = 0, end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;//删除头部多余的空格
        while (s.charAt(end) == ' ') end--;//删除尾部多余的空格
        while (start <= end) {//删除中间多余的空格
            if (s.charAt(start) != ' ' || newS.charAt(newS.length() - 1) != ' ')
                newS.append(s.charAt(start));
            start++;
        }
        return newS;
    }

    //整个字符串进行反转
    public void reverse(StringBuffer s, int i, int j) {
        char temp;
        while (i < j) {
            temp = s.charAt(i);
            s.setCharAt(i, s.charAt(j));
            s.setCharAt(j, temp);
            i++;
            j--;
        }
    }

    //反转字符串中的每个单词
    public void reverseEachWords(StringBuffer s) {
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j < s.length() && s.charAt(j) != ' ')//确定单词边界
                j++;
            reverse(s, i, j - 1);//反转当前单词
            i = j + 1;
        }
    }
}
