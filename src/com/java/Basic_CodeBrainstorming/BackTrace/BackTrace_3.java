package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BackTrace_3
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * @Author chen_sir
 * @Create 2024/4/8 9:47
 * @Version 1.0
 */
public class BackTrace_3 {
    @Test
    public void test() {
        new Solution3().letterCombinations("23478");
    }
}

class Solution3 {
    private Map<Integer, String> map = new HashMap<>();

    //初始所有的数字及其对应的字符串
    public Solution3() {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
    }

    //设置全局列表存储最后的结果
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return result;
        letterTrace(digits, 0);
        for (String s : result) {
            System.out.println(s);
        }
        return result;
    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    //str为拼接后的字符串
    StringBuilder str = new StringBuilder();

    public void letterTrace(String digits, int num) {//num用于记录当前指向的字符串位置
        if (str.length() == digits.length()) {//拼接后的字符串长度等于所给数字字符串的长度，存入结果中
            result.add(str.toString());
            return;
        }
        //temp 表示当前num对应的字符串
        String temp = map.get(digits.charAt(num) - '0');
        for (int i = 0; i < temp.length(); i++) {
            str.append(temp.charAt(i));
            letterTrace(digits, num + 1);
            //剔除末尾的继续尝试
            str.deleteCharAt(num);
        }
    }
}
