package com.java.Basic_CodeBrainstorming.StackAndQueue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ClassName: StackAndQueue_3
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * @Author chen_sir
 * @Create 2024/3/23 18:42
 * @Version 1.0
 */
public class StackAndQueue_3 {
    @Test
    public void test() {
        String s = "[";
        System.out.println(Solution.isValid(s));
    }
}

class Solution {
    //法一：常规思路，将相应的左括号入栈，然后根据右括号的类型和栈顶的左括号进行匹配，注意特殊条件的判断，如栈为空，匹配右括号的情况，时间复杂度为O(n)，空间复杂度为O(n)
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();//将符号字符串转为字符数组，便于操作
        Deque<Character> stack = new ArrayDeque<>();//用于存储括号类型的栈
        for (char c : charArray) {
            if (c == '(' || c == '{' || c == '[')//当前括号为左括号，入栈
                stack.push(c);
            else {
                if (stack.isEmpty()) return false;//当前括号为右括号，且栈已经为空，说明没有相应的左括号与之匹配，返回false
                //栈不为空，说明栈中有左括号，则根据右括号类型来匹配，匹配成功则左括号出栈，不成功说明括号不匹配，返回false
                if (c == ')') {
                    if (stack.peek() == '(')
                        stack.pop();
                    else
                        return false;
                } else if (c == '}') {
                    if (stack.peek() == '{')
                        stack.pop();
                    else
                        return false;
                } else {
                    if (stack.peek() == '[')
                        stack.pop();
                    else
                        return false;
                }
            }
        }
        //全部匹配完毕则栈一定为空，不为空说明不匹配
        return stack.isEmpty();
    }

    //法二：法一的逆向思路，遍历到左括号时将其对应的右括号入栈，遍历到右括号时与栈顶元素判断是否相等，相等则出栈，继续匹配；时间复杂度O(n)，空间复杂度O(n)
    public static boolean isValid2(String s) {
        char[] charArray = s.toCharArray();//将符号字符串转为字符数组，便于操作
        Deque<Character> stack = new ArrayDeque<>();//用于存储括号类型的栈
        for (char c : charArray) {
            //遍历到左括号，则将其相应的右括号入栈
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.peek() != c)//遍历到右括号，判断栈是否为空或者是否与栈顶元素相同
                return false;
            else//栈不为空且当前右括号与栈顶元素相同，栈顶元素出栈，继续匹配
                stack.pop();
        }
        //全部匹配完毕则栈一定为空，不为空说明不匹配
        return stack.isEmpty();
    }
}