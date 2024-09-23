package com.java.Basic_CodeBrainstorming.BackTrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BackTrace_7
 * Package: com.java.Basic_CodeBrainstorming.BackTrace
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/4/11 14:13
 * @Version 1.0
 */
public class BackTrace_7 {
    @Test
    public void test() {
        new Solution7().restoreIpAddresses("101023");
    }
}

class Solution7 {
    List<String> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        restoreTrace(s, 0);
        for (String string : result) {
            System.out.println(string);
        }
        return result;
    }


    //思路：递归的退出条件为分割线遍历至尾部，且正好分割出四个满足条件的数字，此时存入结果集中
    public void restoreTrace(String s, int startIndex) {
        if (startIndex == s.length() && path.size() == 4) {// 分割线到ip字段末尾，且分割数为四，满足要求，放入结果集中
            StringBuilder str = new StringBuilder();
            //遍历结果，将非法数字（以0开头）的去除
            for (String string : path) {
                if (string.charAt(0) == '0' && string.length() > 1)
                    return;
            }
            for (int i = 0; i < path.size() - 1; i++) {
                str.append(path.get(i) + ".");
            }
            str.append(path.get(path.size() - 1));
            result.add(str.toString());
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (path.size() == 4)// 剪枝
                break;
            StringBuilder temp = new StringBuilder(s.substring(startIndex, i + 1));//分割数字
            if (Long.parseLong(temp.toString()) < 0 || Long.parseLong(temp.toString()) > 255)// 取出非法数字
                break;
            path.add(temp.toString());
            restoreTrace(s, i + 1);
            path.remove(path.size() - 1);// 回溯
        }
    }
}
