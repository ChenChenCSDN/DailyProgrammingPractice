package com.java.Basic_CodeBrainstorming.String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: String_4
 * Package: com.java.Basic_CodeBrainstorming.String
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/17 17:06
 * @Version 1.0
 */
public class String_4 {
    @Test
    public void test() {
        String s = "Hello world I";
        System.out.println(reverseWords2(s));
    }


    //方法1：双指针提取字符串中的单词，将其按顺序存入到List集合中，再从List集合中取出单词进行拼接，难点在于空格的插入时机的把握，
    // 时间复杂度为O(n)，空间复杂度O(n)，因为string是常量，每创建一个不同的字符串都会new操作
    public static String reverseWords(String s) {
        char[] charArray = s.toCharArray();
        List<String> list = new ArrayList<>();//存储从字符串中提取到的单词
        int i = 0;
        while (i < s.length()) {
            //通过分隔空格的方式查找单词，双指针分别指向单词的边界
            while (i < s.length() && charArray[i] == ' ')//左边界为空格，继续往前移动
                i++;
            int j = i;
            while (j < s.length() && charArray[j] != ' ')//右边界确定单词的长度
                j++;
            if (i < s.length())//防止末尾都是空格的情况下插入空格
                list.add(s.substring(i, j));//将边界内的单词插入到List集合中
            i = j + 1;
        }
        String result = "";
        i = list.size() - 1;
        //将单词从list中逆序取出，实现逆置
        while (i >= 0) {
            if (i == list.size() - 1)//第一个单词不用插入空格
                result = result.concat(list.get(i));
            else {//后面的单词要插入空格及单词
                result = result.concat(" " + list.get(i));
            }
            i--;
        }
        return result;
    }

    //方法2：先移除原字符串的头尾多余空格，再反转整个字符串，最后反转每个单词，时间复杂度为O(n)，空间复杂度O(1)，StringBuilder为变量，可原地修改
    public static String reverseWords2(String s) {
        StringBuilder result = removeSpace(s);//移除头尾多余的空格
        reverseString(result, 0, s.length() - 1);//反转整个数组
        reverseEachWord(result);//反转每个单词
        return result.toString();
    }

    //原字符串移除头尾多余空格
    public static StringBuilder removeSpace(String s) {
        int start = 0, end = s.length() - 1;
        StringBuilder newString = new StringBuilder();
        while (s.charAt(start) == ' ') start++;//移除头部多余空格
        while (s.charAt(end) == ' ') end--;//移除尾部多余空格
        while (start <= end) {//移除内部多余空格
            char c = s.charAt(start);
            if (c != ' ' || newString.charAt(newString.length() - 1) != ' ')
                newString.append(c);
            start++;
        }
        return newString;
    }

    //反转整个数组
    public static void reverseString(StringBuilder s, int i, int j) {
        char temp;
        while (i < j) {
            temp = s.charAt(i);
            s.setCharAt(i, s.charAt(j));
            s.setCharAt(j, temp);
            i++;
            j--;
        }
    }

    //反转每个单词
    public static void reverseEachWord(StringBuilder s) {
        int start = 0, end = 1;
        while (start < s.length()) {
            while (end < s.length() && s.charAt(end) != ' ')//确定单词边界
                end++;
            reverseString(s, start, end - 1);
            start = ++end;
        }
    }
}
