package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: BackTrace_6
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
 * 回文串
 *  。返回 s 所有可能的分割方案。
 * @Author chen_sir
 * @Create 2024/4/10 10:35
 * @Version 1.0
 */
public class BackTrace_6 {
    @Test
    public void test() {
        new Solution6().partition("aab");
    }
}

class Solution6 {
    List<List<String>> result = new ArrayList<>();// 存放符合条件结果的集合
    List<String> path = new ArrayList<>();// 用来存放符合条件结果

    public List<List<String>> partition(String s) {
        partitionTrace(s, 0);
        for (List<String> strings : result) {
            System.out.println(strings);
        }
        return result;
    }

    public void partitionTrace(String s, int startIndex) {
        if (startIndex == s.length()) {// 当前分割线到字符串尾，表示一次分割完毕，结果存入结果集中
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPartion(s, startIndex, i) == true) {// 当前子串是回文串，存入path条件结果集中
                String str = s.substring(startIndex, i + 1);
                path.add(str);
            } else {
                continue;
            }
            partitionTrace(s, i + 1);
            path.remove(path.size() - 1);//回溯
        }
    }

    //判断子串是否是回文串，采用双指针的方法
    public boolean isPartion(String s, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    //错误思路
    public List<List<String>> partition2(String s) {
        partitionTrace2(new ArrayList<String>(), s, 0);
        for (List<String> strings : result) {
            System.out.println(strings);
        }
        return result;
    }

    StringBuilder str = new StringBuilder();

    public void partitionTrace2(List<String> path, String s, int startIndex) {
        if (str.length() != 0 && isPartion2(str.toString()) == true) {
            result.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (i > startIndex && s.charAt(i) == s.charAt(i - 1))
                continue;
            str.append(s.charAt(i));
            path.add(str.toString());
            partitionTrace2(path, s, i + 1);
            str = str.deleteCharAt(str.length() - 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPartion2(String s) {
        StringBuilder str = new StringBuilder(s).reverse();
        String temp = str.toString();
        return temp.equals(s);
    }
}
