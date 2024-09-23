package com.java.Basic_CodeBrainstorming.StackAndQueue;

import org.junit.Test;

import java.io.FileReader;
import java.util.*;

/**
 * ClassName: StackAndQueue_5
 * Package: com.java.Basic_CodeBrainstorming.StackAndQueue
 * Description:
 *
 * @Author chen_sir
 * @Create 2024/3/25 14:15
 * @Version 1.0
 */
public class StackAndQueue_5 {
    @Test
    public void test() {
        String[] s = new String[]{"2", "1", "+", "3", "*"};
        Solution5.evalRPN(s);
    }
}

class Solution5 {
    /*
    * 逆波兰表达式严格遵循「从左到右」的运算。计算逆波兰表达式的值时，使用一个栈存储操作数，从左到右遍历逆波兰表达式，进行如下操作：
    如果遇到操作数，则将操作数入栈；
    如果遇到运算符，则将两个操作数出栈，其中先出栈的是右操作数，后出栈的是左操作数，使用运算符对两个操作数进行运算，将运算得到的新操作数入栈。
    整个逆波兰表达式遍历完毕之后，栈内只有一个元素，该元素即为逆波兰表达式的值。
    * */
    //时间复杂度O(n)，空间复杂度O(n)
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();//用于存储操作数和操作结果的栈
        for (String item : tokens) {
            if ("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {//当前字符为操作符，执行运算操作
                int lastNumber = stack.pop();//出栈的第一个元素为右操作数
                int firstNumber = stack.pop();//出栈的第二个元素为左操作数
                //判断操作符的类型，将执行结果入栈
                if ("+".equals(item)) stack.push(firstNumber+lastNumber);
                else if ("-".equals(item)) stack.push(firstNumber-lastNumber);
                else if ("*".equals(item)) stack.push(firstNumber*lastNumber);
                else if ("/".equals(item)) stack.push(firstNumber/lastNumber);
            } else {
                stack.push(Integer.valueOf(item));//当前字符为整数，入栈
            }
        }
        //运行完毕后，栈内剩下的一个元素就是运算结果
        return stack.peek();
    }
}
