package com.java.Basic_CodeBrainstorming.StackAndQueue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * ClassName: StackAndQueue_4
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/23 19:35
 * @Version 1.0
 */
public class StackAndQueue_4 {
    @Test
    public void test() {
        String s = "abbaca";
        System.out.println(Solution2.removeDuplicates(s));
    }
}

class Solution2 {
    //思路：遍历字符串，判断当前字符与栈顶元素是否相同，相同则出栈，不同则入栈，最后将栈中剩余元素逆序转为字符串即可，时间复杂度: O(n)，空间复杂度：O(n)
    public static String removeDuplicates(String s) {
        char[] charArray = s.toCharArray();//将字符串转为字符数组，便于操作
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : charArray) {//遍历字符串
            if (!stack.isEmpty() && stack.peek() == c)//当前字符与栈顶元素相同，则出栈，继续查找
                stack.pop();
            else//不同则入栈
                stack.push(c);
        }
        int index = stack.size();//边界索引
        while (!stack.isEmpty()) {//将栈中元素依次取出，逆序插入到字符数组中
            charArray[stack.size() - 1] = stack.pop();
        }
        return String.valueOf(charArray).substring(0, index);//返回结果
    }
}
